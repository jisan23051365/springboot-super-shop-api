package com.supershop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Product ID must not be null")
    @Column(nullable = false)
    private Long productId;

    @NotNull(message = "Customer ID must not be null")
    @Column(nullable = false)
    private Long customerId;

    @Positive(message = "Quantity must be positive")
    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double totalPrice;
}
