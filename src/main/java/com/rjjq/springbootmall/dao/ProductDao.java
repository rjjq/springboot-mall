package com.rjjq.springbootmall.dao;

import com.rjjq.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
