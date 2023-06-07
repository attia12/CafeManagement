package com.inn.cafe2.com.inn.cafe2.service;

import com.inn.cafe2.com.inn.cafe2.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ResponseEntity<String>addNewProduct(Map<String,String> requestMap);
    ResponseEntity<List<ProductWrapper>>getAllProduct();
    ResponseEntity<String>updateProduct(Map<String,String> requestMap);
    ResponseEntity<String>deleteProduct(Integer id);
    ResponseEntity<String>updateStatus(Map<String,String> requestMap);
    ResponseEntity<List<ProductWrapper>>getByCategory(Integer id);

    ResponseEntity<ProductWrapper>getProductById(Integer id);
}
