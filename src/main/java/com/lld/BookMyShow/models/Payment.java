package com.lld.BookMyShow.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseModel{

    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private Double amount;
    private String referenceId;
}
