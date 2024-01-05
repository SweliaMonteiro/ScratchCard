package com.test.scratchcard.repositories;

import com.test.scratchcard.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Override
    Optional<Item> findById(Integer id);

}
