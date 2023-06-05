package com.inn.cafe2.com.inn.cafe2.dao;

import com.inn.cafe2.com.inn.cafe2.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category,Integer> {
    List<Category> getAllCategory();
}
