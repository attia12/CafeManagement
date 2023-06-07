package com.inn.cafe2.com.inn.cafe2.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface BillService {
    ResponseEntity<String>generateReport(Map<String,Object>requestMap);
}
