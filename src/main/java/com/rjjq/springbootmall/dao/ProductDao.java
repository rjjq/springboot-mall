package com.rjjq.springbootmall.dao;

import com.rjjq.springbootmall.constant.ProductCategory;
import com.rjjq.springbootmall.dto.ProductRequest;
import com.rjjq.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
