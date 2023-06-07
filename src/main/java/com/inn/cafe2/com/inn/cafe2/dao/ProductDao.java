package com.inn.cafe2.com.inn.cafe2.dao;

import com.inn.cafe2.com.inn.cafe2.models.Product;
import com.inn.cafe2.com.inn.cafe2.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {
    List<ProductWrapper>getAllProduct();
}
