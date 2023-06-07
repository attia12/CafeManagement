package com.inn.cafe2.com.inn.cafe2.service;

import com.inn.cafe2.com.inn.cafe2.models.Category;
import com.inn.cafe2.com.inn.cafe2.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    ResponseEntity<String>addNewCategory(Map<String,String>requestMap);
    ResponseEntity<List<Category>>getAllCategory(String filterValue);
    ResponseEntity<String>updateCategory(Map<String,String>requestMap);

}
