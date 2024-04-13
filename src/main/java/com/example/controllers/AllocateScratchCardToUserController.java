package com.example.controllers;

import com.example.dto.AllocateScratchCardToUserResponseDTO;
import com.example.enums.ResponseStatus;
import com.example.services.AllocateScratchCardToUserService;
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
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e) {
            response.setFailureMessage(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }

}
