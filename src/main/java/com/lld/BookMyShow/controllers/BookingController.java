package com.lld.BookMyShow.controllers;

import com.lld.BookMyShow.dtos.BookMovieRequestDto;
import com.lld.BookMyShow.dtos.BookMovieResponseDto;
import com.lld.BookMyShow.models.Booking;
import com.lld.BookMyShow.services.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookmyshow/v1/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookMovieResponseDto bookMovieTicket(@RequestBody BookMovieRequestDto req){
        Booking booking = bookingService.bookMovieTicket(req.getUserId(), req.getShowId(), req.getSeatIds());

        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();
        return bookMovieResponseDto;
    }

}
