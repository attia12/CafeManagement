package com.inn.cafe2.com.inn.cafe2.dao;

import com.inn.cafe2.com.inn.cafe2.models.Product;
import com.inn.cafe2.com.inn.cafe2.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {
    List<ProductWrapper>getAllProduct();
    @Transactional
    @Modifying
    Integer updateProductStatus(@Param("status") String status,@Param("id") Integer id);
    List<ProductWrapper>getByCategory(@Param("id") Integer id);
    ProductWrapper getProductById(@Param("id") Integer id);
}
