package com.example.dto;

import com.example.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTransactionResponseDTO {

    private double totalAmount;

    private ResponseStatus responseStatus;

    private String failureMessage;

}
