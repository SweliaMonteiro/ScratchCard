package com.test.scratchcard.controllers;

import com.test.scratchcard.dto.AllocateScratchCardToUserResponseDTO;
import com.test.scratchcard.enums.ResponseStatus;
import com.test.scratchcard.services.AllocateScratchCardToUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AllocateScratchCardToUserController {

    @Autowired
    private AllocateScratchCardToUserService allocateScratchCardToUserService;


    @GetMapping("/allocateScratchCardToUser/{userId}")
    public AllocateScratchCardToUserResponseDTO allocateScratchCardToUser(@PathVariable int userId) {
        AllocateScratchCardToUserResponseDTO response = new AllocateScratchCardToUserResponseDTO();

        try {
            String scratchCardGUID = allocateScratchCardToUserService.allocateScratchCardToUser(userId);
            response.setScratchCardGUID(scratchCardGUID);
            response.setResponseStatus(com.test.scratchcard.enums.ResponseStatus.SUCCESS);
        }
        catch (Exception e) {
            response.setFailureMessage(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }

}
