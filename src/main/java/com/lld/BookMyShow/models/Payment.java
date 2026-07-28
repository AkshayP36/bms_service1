package com.lld.BookMyShow.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private PaymentMode paymentMode;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private PaymentStatus paymentStatus;

    private Double amount;
    private String referenceId;
}
