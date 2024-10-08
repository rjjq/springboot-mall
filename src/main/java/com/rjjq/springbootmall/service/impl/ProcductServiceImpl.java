package com.rjjq.springbootmall.service.impl;

import com.rjjq.springbootmall.dao.ProductDao;
import com.rjjq.springbootmall.dto.ProductQueryPrams;
import com.rjjq.springbootmall.dto.ProductRequest;
import com.rjjq.springbootmall.model.Product;
import com.rjjq.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProcductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(ProductQueryPrams productQueryPrams) {
        return productDao.getProducts(productQueryPrams);
    }

    @Override
    public Integer countProduct(ProductQueryPrams productQueryPrams) {
        return productDao.countProduct(productQueryPrams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }
}
