package com.example.controllers;

import com.example.dto.OrderTransactionRequestDTO;
import com.example.dto.OrderTransactionResponseDTO;
import com.example.enums.ResponseStatus;
import com.example.models.Order;
import com.example.services.OrderTransactionService;
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
