package com.rjjq.springbootmall.dao.impl;

import com.rjjq.springbootmall.constant.ProductCategory;
import com.rjjq.springbootmall.dao.ProductDao;
import com.rjjq.springbootmall.dto.ProductQueryPrams;
import com.rjjq.springbootmall.dto.ProductRequest;
import com.rjjq.springbootmall.model.Product;
import com.rjjq.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryPrams productQueryPrams) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date FROM product WHERE 1=1" ;
        Map<String, Object> map = new HashMap<>();

        sql = addFilteringSql(sql, map, productQueryPrams);

        String orderBy = productQueryPrams.getOrderBy();
        String sort = productQueryPrams.getSort();

        sql += " ORDER BY " + orderBy + " " + sort;

        sql += " LIMIT :limit OFFSET :offset";

        map.put("limit", productQueryPrams.getLimit());
        map.put("offset", productQueryPrams.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return productList;
    }

    @Override
    public Integer countProduct(ProductQueryPrams productQueryPrams) {
        String sql = "SELECT count(*) FROM product WHERE 1=1" ;
        Map<String, Object> map = new HashMap<>();

        sql = addFilteringSql(sql, map, productQueryPrams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    private static String addFilteringSql(String sql, Map<String, Object> map, ProductQueryPrams productQueryPrams) {
        ProductCategory category = productQueryPrams.getCategory();
        String search = productQueryPrams.getSearch();

        if (category != null) {
            sql += " AND category = :category";
            map.put("category", category.name());
        }

        if (search != null) {
            sql += " AND product_name like :search";
            map.put("search", "%"+search+"%");
        }
        return sql;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date FROM product WHERE product_id = :productId;";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if (productList.size()>0){
            return productList.get(0);
        } else {
            return null;
        }

    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES (:product_name, :category, :image_url, :price, :stock, :description, :created_date, :last_modified_date);";

        Map<String, Object> map = new HashMap<>();
        map.put("product_name", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("created_date", now);
        map.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :product_name, category = :category, image_url = :image_url, price = :price, stock = :stock, description = :description, last_modified_date = :last_modified_date WHERE product_id = :product_id;" ;

        Map<String, Object> map = new HashMap<>();
        map.put("product_id", productId);
        map.put("product_name", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        map.put("last_modified_date", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId;" ;

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void updateStock(Integer product_id, int stock) {
        String sql = "UPDATE product SET stock = :stock, last_modified_date = :lastModifiedDate" +
                " WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("stock", stock);
        map.put("productId", product_id);
        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }
}
