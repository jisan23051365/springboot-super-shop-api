package com.supershop.controller;

import com.supershop.entity.Customer;
import com.supershop.entity.OrderSale;
import com.supershop.entity.Product;
import com.supershop.service.CustomerService;
import com.supershop.service.OrderSaleService;
import com.supershop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;
    private final CustomerService customerService;
    private final OrderSaleService orderSaleService;

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderSale> createOrder(@Valid @RequestBody OrderSale orderSale) {
        OrderSale savedOrder = orderSaleService.createOrder(orderSale);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderSale>> getAllOrders() {
        return ResponseEntity.ok(orderSaleService.getAllOrders());
    }
}
