package com.svenfran.ecommerce.dto;

import com.svenfran.ecommerce.entity.Address;
import com.svenfran.ecommerce.entity.Customer;
import com.svenfran.ecommerce.entity.Order;
import com.svenfran.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
