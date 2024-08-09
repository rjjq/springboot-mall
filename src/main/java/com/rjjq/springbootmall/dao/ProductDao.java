package com.rjjq.springbootmall.dao;

import com.rjjq.springbootmall.dto.ProductQueryPrams;
import com.rjjq.springbootmall.dto.ProductRequest;
import com.rjjq.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductQueryPrams productQueryPrams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    Integer countProduct(ProductQueryPrams productQueryPrams);

    void updateStock(Integer product_id, int i);
}
