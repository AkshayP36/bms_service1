package com.lld.BookMyShow.services;

import com.lld.BookMyShow.dtos.BookMovieRequestDto;
import com.lld.BookMyShow.dtos.BookMovieResponseDto;
import com.lld.BookMyShow.models.Booking;
import com.lld.BookMyShow.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking bookMovieTicket(Long userId, Long showId, List<Long> seatIds) {
        return null;
    }
}
