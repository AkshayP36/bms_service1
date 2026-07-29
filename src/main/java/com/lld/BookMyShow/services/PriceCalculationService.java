package com.lld.BookMyShow.services;

import com.lld.BookMyShow.models.Show;
import com.lld.BookMyShow.models.ShowSeat;
import com.lld.BookMyShow.models.ShowSeatType;
import com.lld.BookMyShow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculationService {

    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculationService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int getPrice(List<ShowSeat> showSeats, Show show){
        //Get showSeatTYpe by show_id
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAll();

        int amount = 0;

        for(ShowSeat showSeat: showSeats){
            for(ShowSeatType showSeatType: showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(showSeatType)){
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }
}
