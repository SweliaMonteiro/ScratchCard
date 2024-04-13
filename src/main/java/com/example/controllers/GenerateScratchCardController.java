package com.example.controllers;

import com.example.dto.*;
import com.example.enums.ResponseStatus;
import com.example.models.ScratchCard;
import com.example.services.GenerateScratchCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;


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
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e) {
            response.setScratchCardGUIDs(new ArrayList<>());
            response.setFailureMessage(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }

}
