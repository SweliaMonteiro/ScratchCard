package com.test.scratchcard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTransactionRequestDTO {

    private int itemId;
    private int quantity;
    private String scratchCardGUID;

}
