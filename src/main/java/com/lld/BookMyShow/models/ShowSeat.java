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
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat; //meaning: one normal seat in a theatre will become a showseat during let's say 9pm-12pm movie time
    // So, one ShowSeat can be a normal seat. But a normal seat can be a showSeat multiple times during a day ex for 9am-11am show
    //12.30-4pm show and so on


    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;
}
