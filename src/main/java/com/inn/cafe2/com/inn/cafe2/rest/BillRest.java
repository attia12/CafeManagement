package com.inn.cafe2.com.inn.cafe2.rest;

import com.inn.cafe2.com.inn.cafe2.models.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/bill")

public interface BillRest {
    @PostMapping("/generateReport")
    ResponseEntity<String>generateReport(@RequestBody Map<String,Object>requestMap);
    @GetMapping("/getBills")
    ResponseEntity<List<Bill>>getBills();
    @PostMapping(path ="/getPdf")
    ResponseEntity<byte[]>getPdf(@RequestBody Map<String,Object>requestMap);

    @PostMapping(path ="/delete/{id}")
    ResponseEntity<String>deleteBill(@PathVariable Integer id);
}
