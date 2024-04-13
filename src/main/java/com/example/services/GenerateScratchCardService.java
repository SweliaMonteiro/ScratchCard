package com.example.services;

import com.example.models.ScratchCard;
import com.example.repositories.ScratchCardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.*;
import java.sql.Date;
import java.util.*;


@Service
public class GenerateScratchCardService {

    @Autowired
    private ScratchCardRepository scratchCardRepository;


    @Transactional
    public List<ScratchCard> generateScratchCards(int numberOfScratchCards, int expiredAfterDays, int discount, boolean flag) throws Exception {
        // If flag is true, all existing unused scratch cards will need to deactivate
        if(flag) {
            // Deactivate unused scratch cards
            scratchCardRepository.updateAllActiveScratchCards();
        }

        // Generate new scratch cards with the given number
        List<ScratchCard> scratchCards = new ArrayList<>();
        for(int i=1; i<=numberOfScratchCards; i++) {
            ScratchCard scratchCard = new ScratchCard();

            // Set the required values of scratch card
            LocalDate localDate = LocalDate.now().plusDays(expiredAfterDays);
            scratchCard.setScratchCardExpiryDate(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

            // Generate a random UUID for scratch card
            scratchCard.setScratchCardGUID(UUID.randomUUID().toString());

            // Add discount percentage
            scratchCard.setDiscount(discount);

            // Mark the scratch card active
            scratchCard.setActive(true);

            // Save the new scratch card in DB
            scratchCardRepository.save(scratchCard);

            scratchCards.add(scratchCard);
        }

        return scratchCards;
    }

}
