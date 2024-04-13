package com.example.repositories;

import com.example.models.ScratchCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ScratchCardIRepository extends JpaRepository<ScratchCard, Integer> {

    Optional<ScratchCard> findByScratchCardGUID(String scratchCardGUID);

    List<ScratchCard> findAllByIsActive(boolean isActive);

}
