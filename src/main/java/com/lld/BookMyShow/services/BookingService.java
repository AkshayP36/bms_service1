package com.lld.BookMyShow.services;

import com.lld.BookMyShow.exceptions.SeatNotAvailableException;
import com.lld.BookMyShow.exceptions.ShowNotFoundException;
import com.lld.BookMyShow.exceptions.UserNotFoundException;
import com.lld.BookMyShow.models.*;
import com.lld.BookMyShow.repositories.ShowRepository;
import com.lld.BookMyShow.repositories.ShowSeatRepository;
import com.lld.BookMyShow.repositories.ShowSeatTypeRepository;
import com.lld.BookMyShow.repositories.UserRepository;
import com.zaxxer.hikari.util.IsolationLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private ShowSeatTypeRepository showSeatTypeRepository;
    private UserRepository userRepository;
    private PriceCalculationService priceCalculationService;

    public BookingService(ShowRepository showRepository, ShowSeatRepository showSeatRepository, ShowSeatTypeRepository showSeatTypeRepository, UserRepository userRepository, PriceCalculationService priceCalculationService) {
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.showSeatTypeRepository = showSeatTypeRepository;
        this.userRepository = userRepository;
        this.priceCalculationService = priceCalculationService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovieTicket(Long userId, Long showId, List<Long> seatIds) throws UserNotFoundException, ShowNotFoundException, SeatNotAvailableException {
        /*
        1. Get user info. if user is not valid throw exception
        2. Get show info. If show is not valid throw exception
        3. Get all showSeatIds, using seatIds.
        4. check if all seats are available/empty
        5. Take a lock
        6. Check the status again [double check lock]
        7. mark the seat status to blocked
        8. Release the lock
        9. proceed to payments
         */

        Optional<User> userCheck = userRepository.findById(userId);
        if(userCheck.isEmpty()){
            throw new UserNotFoundException("Invalid user");
        }
        User user = userCheck.get(); //current user

        Optional<Show> showCheck = showRepository.findById(showId);
        if(showCheck.isEmpty()){
            throw new ShowNotFoundException("Invalid show");
        }
        Show currentShow = showCheck.get(); //current show

        List<ShowSeat> showSeats = showSeatRepository.findAllById(seatIds); // I am finding for the seat numbers selected by
        //user ex. there are total 100 seats in the theatre, user has selected seat number 30,31 and 32. So, first checking if
        //those seats are valid seats
        for(ShowSeat showSeat: showSeats){   //here checking the status of seat number 30,31 and 32
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.OCCUPIED)){
                throw new SeatNotAvailableException("Sorry these seats are not available");
            }
        }


        //Now as seats are available to book, BLOCK the seats for current user, so next thread can't creae a run condition
        for(ShowSeat showSeat: showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);// saving seat is Blocked in DB also
        }


        Booking newBooking = new Booking();
        newBooking.setBookedBy(user);
        newBooking.setShowSeats(showSeats);
        newBooking.setAmount(priceCalculationService.getPrice(showSeats, currentShow));


        //Call payment service
//        newBooking.setPayments();
        //Make payment code


        return newBooking;
    }
}
