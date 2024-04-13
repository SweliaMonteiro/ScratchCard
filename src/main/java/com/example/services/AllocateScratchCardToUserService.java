package com.example.services;

import com.example.repositories.*;
import com.example.exceptions.*;
import com.example.models.*;
import com.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class AllocateScratchCardToUserService {

    @Autowired
    private ScratchCardRepository scratchCardRepository;

    @Autowired
    private ScratchCardIRepository scratchCardIRepository;

    @Autowired
    private UserRepository userRepository;


    public String allocateScratchCardToUser(int userId) throws Exception {
        // Check if userId is valid, is active
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if(userOptional.isEmpty() || !userOptional.get().isActive()) {
            throw new InvalidUser("No User found with the given ID");
        }
        User user = userOptional.get();

        // Find any active scratch card available
        List<ScratchCard> scratchCards = scratchCardIRepository.findAllByIsActive(true);
        String scratchCardGUID = "";
        for(ScratchCard scratchCard : scratchCards) {
            if(scratchCard.getUser() == null) {
                scratchCardGUID = scratchCard.getScratchCardGUID();
            }
        }

        // If no active scratch cards available then throw error
        if(scratchCards.size()==0 || scratchCardGUID.length()==0) {
            throw new InvalidScratchCard("No active Scratch Cards available.");
        }

        // Allocate User to scratch card
        scratchCardRepository.allocateUserToScratchCard(user.getId(), scratchCardGUID);

        return scratchCardGUID;
    }

}
