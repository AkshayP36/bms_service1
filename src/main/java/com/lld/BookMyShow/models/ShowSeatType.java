package com.lld.BookMyShow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatType extends BaseModel{

    private int price;

    @ManyToOne
    private Show show;

    @ManyToOne
    private SeatType seatType;
}
