package com.supershop.repository;

import com.supershop.entity.OrderSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSaleRepository extends JpaRepository<OrderSale, Long> {
}
