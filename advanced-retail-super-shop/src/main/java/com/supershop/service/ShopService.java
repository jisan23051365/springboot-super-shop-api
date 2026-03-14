package com.supershop.service;

import com.supershop.entity.*;
import com.supershop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ProductRepository productRepo;
    private final CustomerRepository customerRepo;
    private final OrderRepository orderRepo;

    public Product addProduct(Product p){
        return productRepo.save(p);
    }

    public Customer addCustomer(Customer c){
        return customerRepo.save(c);
    }

    public OrderSale createOrder(Long productId,Long customerId,int qty){

        Product product = productRepo.findById(productId).orElseThrow();

        if(product.getStock() < qty){
            throw new RuntimeException("Not enough stock");
        }

        product.setStock(product.getStock() - qty);
        productRepo.save(product);

        OrderSale order = new OrderSale();
        order.setProductId(productId);
        order.setCustomerId(customerId);
        order.setQuantity(qty);
        order.setTotalPrice(product.getPrice()*qty);

        return orderRepo.save(order);

    }

    public List<Product> getProducts(){
        return productRepo.findAll();
    }

    public List<OrderSale> getOrders(){
        return orderRepo.findAll();
    }

}