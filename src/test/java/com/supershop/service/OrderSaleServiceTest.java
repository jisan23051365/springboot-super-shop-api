package com.supershop.service;

import com.supershop.entity.Customer;
import com.supershop.entity.OrderSale;
import com.supershop.entity.Product;
import com.supershop.exception.InsufficientStockException;
import com.supershop.repository.OrderSaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderSaleServiceTest {

    @Mock
    private OrderSaleRepository orderSaleRepository;

    @Mock
    private ProductService productService;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private OrderSaleService orderSaleService;

    private Product product;
    private Customer customer;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "Widget", 10.0, 5);
        customer = new Customer(1L, "Alice", "555-1234");
    }

    @Test
    void createOrder_sufficientStock_deductsStockAndSavesOrder() {
        OrderSale orderSale = new OrderSale(null, 1L, 1L, 3, 0.0);

        when(productService.getProductById(1L)).thenReturn(product);
        when(customerService.getCustomerById(1L)).thenReturn(customer);
        when(productService.saveProduct(any(Product.class))).thenReturn(product);
        when(orderSaleRepository.save(any(OrderSale.class))).thenAnswer(inv -> inv.getArgument(0));

        OrderSale result = orderSaleService.createOrder(orderSale);

        assertEquals(30.0, result.getTotalPrice());
        assertEquals(2, product.getStock());
        verify(productService, times(1)).saveProduct(product);
        verify(orderSaleRepository, times(1)).save(orderSale);
    }

    @Test
    void createOrder_insufficientStock_throwsInsufficientStockException() {
        OrderSale orderSale = new OrderSale(null, 1L, 1L, 10, 0.0);

        when(productService.getProductById(1L)).thenReturn(product);
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        assertThrows(InsufficientStockException.class, () -> orderSaleService.createOrder(orderSale));

        verify(productService, never()).saveProduct(any());
        verify(orderSaleRepository, never()).save(any());
    }
}
