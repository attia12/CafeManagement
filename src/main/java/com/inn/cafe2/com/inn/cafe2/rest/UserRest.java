package com.inn.cafe2.com.inn.cafe2.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/user")
public interface UserRest {
    @PostMapping("/signup")
    public ResponseEntity<String>signUP(@RequestBody Map<String,String>requestMapping);
    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody Map<String,String>requestMapping);
}
