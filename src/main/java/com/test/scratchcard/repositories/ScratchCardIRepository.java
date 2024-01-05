package com.test.scratchcard.repositories;

import com.test.scratchcard.models.ScratchCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScratchCardIRepository extends JpaRepository<ScratchCard, Integer> {

    Optional<ScratchCard> findByScratchCardGUID(String scratchCardGUID);

    List<ScratchCard> findAllByIsActive(boolean isActive);

}
