package com.inn.cafe2.com.inn.cafe2.dao;

import com.inn.cafe2.com.inn.cafe2.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDao extends JpaRepository <Bill,Integer> {
}
