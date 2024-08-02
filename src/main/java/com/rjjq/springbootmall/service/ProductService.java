package com.rjjq.springbootmall.service;

import com.rjjq.springbootmall.dto.ProductRequest;
import com.rjjq.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
