package com.test.scratchcard.repositories;

import com.test.scratchcard.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Override
    Order save(Order order);

}
