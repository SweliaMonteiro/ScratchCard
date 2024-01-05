package com.test.scratchcard.services;

import com.test.scratchcard.exceptions.InvalidItem;
import com.test.scratchcard.exceptions.InvalidScratchCard;
import com.test.scratchcard.models.Item;
import com.test.scratchcard.models.Order;
import com.test.scratchcard.models.ScratchCard;
import com.test.scratchcard.repositories.ItemRepository;
import com.test.scratchcard.repositories.OrderRepository;
import com.test.scratchcard.repositories.ScratchCardIRepository;
import com.test.scratchcard.repositories.ScratchCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderTransactionService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ScratchCardIRepository scratchCardIRepository;

    @Autowired
    private ScratchCardRepository scratchCardRepository;


    public Order orderTransaction(int itemId, int quantity, String scratchCardGUID) throws Exception {
        Order order = new Order();

        // Check if itemId is valid
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if(itemOptional.isEmpty()) {
            throw new InvalidItem("No Item found with the given ID");
        }
        Item item = itemOptional.get();

        // Get discount from DB for given GUID and apply the discount amount
        Optional<ScratchCard> scratchCardOptional = scratchCardIRepository.findByScratchCardGUID(scratchCardGUID);
        // Throw error if no active Scratch Cards available in DB
        if(scratchCardOptional.isEmpty() || !scratchCardOptional.get().isActive()) {
            throw new InvalidScratchCard("No active Scratch Cards available with the given GUID.");
        }

        // Set item and quantity for the Order
        order.setItem(item);
        order.setQuantity(quantity);

        // Calculate total amount
        double totalAmount = item.getRate() * quantity;

        // Apply the discount percentage of scratch card to the total amount
        double totalDiscountAmount = totalAmount * ((double)scratchCardOptional.get().getDiscount() / 100.0);
        totalAmount = totalAmount - totalDiscountAmount;

        // Deactivate used scratch card
        scratchCardRepository.deactivateScratchCard(scratchCardOptional.get().getScratchCardGUID());

        // Set the total amount and scratch card used for the Order
        order.setTotalAmount(totalAmount);
        order.setScratchCard(scratchCardOptional.get());

        // Save the Order in DB
        orderRepository.save(order);

        return order;
    }

}
