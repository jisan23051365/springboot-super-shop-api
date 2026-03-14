package com.supershop.repository;

import com.supershop.entity.OrderSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderSale,Long> {
}