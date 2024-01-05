package com.test.scratchcard.controllers;

import com.test.scratchcard.dto.GenerateScratchCardsRequestDTO;
import com.test.scratchcard.dto.GenerateScratchCardsResponseDTO;
import com.test.scratchcard.enums.ResponseStatus;
import com.test.scratchcard.models.ScratchCard;
import com.test.scratchcard.services.GenerateScratchCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GenerateScratchCardController {

    @Autowired
    private GenerateScratchCardService scratchCardService;


    @GetMapping(value = "/generateScratchCards")
    public GenerateScratchCardsResponseDTO generateScratchCards(GenerateScratchCardsRequestDTO request) {
        GenerateScratchCardsResponseDTO response = new GenerateScratchCardsResponseDTO();

        try {
            List<ScratchCard> scratchCards = scratchCardService.generateScratchCards(request.getNumberOfScratchCards(), request.getExpiredAfterDays(), request.getDiscount(), request.isFlag());
            List<String> scratchCardGUIDs = new ArrayList<>();
            for(ScratchCard scratchCard:scratchCards) {
                scratchCardGUIDs.add(scratchCard.getScratchCardGUID());
            }
            response.setScratchCardGUIDs(scratchCardGUIDs);
            response.setResponseStatus(com.test.scratchcard.enums.ResponseStatus.SUCCESS);
        }
        catch (Exception e) {
            response.setScratchCardGUIDs(new ArrayList<>());
            response.setFailureMessage(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }

}
