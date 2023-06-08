package com.inn.cafe2.com.inn.cafe2.service;

import com.inn.cafe2.com.inn.cafe2.models.Bill;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface BillService {
    ResponseEntity<String>generateReport(Map<String,Object>requestMap);
    ResponseEntity<List<Bill>>getBills();
    ResponseEntity<byte[]>getPdf(Map<String,Object>requestMap);
    ResponseEntity<String>deleteBill(Integer id);
}
