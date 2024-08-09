package com.rjjq.springbootmall.service;

import com.rjjq.springbootmall.dto.CreateOrderRequest;
import com.rjjq.springbootmall.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
