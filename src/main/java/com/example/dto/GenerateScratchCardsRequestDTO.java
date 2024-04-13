package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateScratchCardsRequestDTO {

    private int numberOfScratchCards;

    private int expiredAfterDays;

    private int discount;

    private boolean flag;

}
