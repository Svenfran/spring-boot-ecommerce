package com.svenfran.ecommerce.service;

import com.svenfran.ecommerce.dto.Purchase;
import com.svenfran.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
