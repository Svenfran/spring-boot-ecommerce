package com.svenfran.ecommerce.dao;

import com.svenfran.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String theEmail); // returns null if not found
}
