package com.lld.BookMyShow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel{

    private String bookingNumber;
    @ManyToOne
    private User bookedBy;
    @OneToMany
    private List<ShowSeat> showSeats;
    private int amount;
    @OneToMany
    private List<Payment> payments;

    @OneToOne
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private BookingStatus bookingStatus;
}
