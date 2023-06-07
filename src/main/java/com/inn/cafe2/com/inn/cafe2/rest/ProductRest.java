package com.inn.cafe2.com.inn.cafe2.rest;

import com.inn.cafe2.com.inn.cafe2.wrapper.ProductWrapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/product")

public interface ProductRest {
    @PostMapping(path = "/add")
    ResponseEntity<String> addNewProduct(@RequestBody Map<String,String>requestMap);
    @GetMapping("/get")
    ResponseEntity<List<ProductWrapper>> getAllProduct();
    @PostMapping("/update")
    ResponseEntity<String> updateProduct(@RequestBody Map<String,String>requestMap);
    @PostMapping("/delete/{id}")
    ResponseEntity<String>deleteProduct(@PathVariable Integer id);

    @PostMapping("/updatestatus")
    ResponseEntity<String>updateStatus(@RequestBody Map<String,String>requestMap);
    @GetMapping("/getByCategory/{id}")
    ResponseEntity<List<ProductWrapper>>getByCategory(@PathVariable Integer id);
    @GetMapping("/getById/{id}")
    ResponseEntity<ProductWrapper>getProductById(@PathVariable Integer id);



}
