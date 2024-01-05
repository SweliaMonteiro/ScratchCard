package com.test.scratchcard.controllers;

import com.test.scratchcard.dto.OrderTransactionRequestDTO;
import com.test.scratchcard.dto.OrderTransactionResponseDTO;
import com.test.scratchcard.enums.ResponseStatus;
import com.test.scratchcard.models.Order;
import com.test.scratchcard.services.OrderTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderTransactionController {

    @Autowired
    private OrderTransactionService orderTransactionService;


    @GetMapping("/orderTransaction")
    public OrderTransactionResponseDTO orderTransaction(OrderTransactionRequestDTO request) {
        OrderTransactionResponseDTO response = new OrderTransactionResponseDTO();

        try {
            Order order = orderTransactionService.orderTransaction(request.getItemId(), request.getQuantity(), request.getScratchCardGUID());
            response.setTotalAmount(order.getTotalAmount());
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e) {
            response.setFailureMessage(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }

}
