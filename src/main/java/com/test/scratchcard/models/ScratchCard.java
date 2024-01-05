package com.test.scratchcard.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class ScratchCard {

    @Id
    private String scratchCardGUID;

    private int discount;

    private Date scratchCardExpiryDate;

    private boolean isActive;

    @ManyToOne
    private User user;

    @OneToOne
    private Item item;

}
