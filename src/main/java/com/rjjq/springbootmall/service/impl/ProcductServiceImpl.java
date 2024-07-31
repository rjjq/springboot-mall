package com.rjjq.springbootmall.service.impl;

import com.rjjq.springbootmall.dao.ProductDao;
import com.rjjq.springbootmall.model.Product;
import com.rjjq.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
