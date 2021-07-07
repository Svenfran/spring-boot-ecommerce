package com.svenfran.ecommerce.service;

import com.svenfran.ecommerce.dao.CustomerRepository;
import com.svenfran.ecommerce.dto.Purchase;
import com.svenfran.ecommerce.dto.PurchaseResponse;
import com.svenfran.ecommerce.entity.Customer;
import com.svenfran.ecommerce.entity.Order;
import com.svenfran.ecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve teh order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = gererateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer  customer = purchase.getCustomer();
        customer.add(order);

        // save to the db
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String gererateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // For details see: wiki -> universally_unique_identifier

        return UUID.randomUUID().toString();
    }
}
