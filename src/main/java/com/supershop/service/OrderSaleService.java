package com.supershop.service;

import com.supershop.entity.Customer;
import com.supershop.entity.OrderSale;
import com.supershop.entity.Product;
import com.supershop.exception.InsufficientStockException;
import com.supershop.repository.OrderSaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSaleService {

    private final OrderSaleRepository orderSaleRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    @Transactional
    public OrderSale createOrder(OrderSale orderSale) {
        Product product = productService.getProductById(orderSale.getProductId());

        // Validate customer exists
        Customer customer = customerService.getCustomerById(orderSale.getCustomerId());

        // Check stock availability
        if (product.getStock() < orderSale.getQuantity()) {
            throw new InsufficientStockException(
                    "Insufficient stock for product '" + product.getName() + "'. "
                    + "Available: " + product.getStock() + ", Requested: " + orderSale.getQuantity());
        }

        // Deduct stock
        product.setStock(product.getStock() - orderSale.getQuantity());
        productService.saveProduct(product);

        // Calculate total price
        orderSale.setTotalPrice(product.getPrice() * orderSale.getQuantity());

        return orderSaleRepository.save(orderSale);
    }

    public List<OrderSale> getAllOrders() {
        return orderSaleRepository.findAll();
    }
}
