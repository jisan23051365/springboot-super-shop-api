package com.supershop.controller;

import com.supershop.entity.*;
import com.supershop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService service;

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product p){
        return service.addProduct(p);
    }

    @PostMapping("/customer")
    public Customer addCustomer(@RequestBody Customer c){
        return service.addCustomer(c);
    }

    @PostMapping("/order")
    public OrderSale order(
            @RequestParam Long productId,
            @RequestParam Long customerId,
            @RequestParam int qty){

        return service.createOrder(productId,customerId,qty);

    }

    @GetMapping("/products")
    public List<Product> products(){
        return service.getProducts();
    }

    @GetMapping("/orders")
    public List<OrderSale> orders(){
        return service.getOrders();
    }

}