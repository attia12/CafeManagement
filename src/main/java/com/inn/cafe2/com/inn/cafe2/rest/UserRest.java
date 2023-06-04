package com.inn.cafe2.com.inn.cafe2.rest;

import com.inn.cafe2.com.inn.cafe2.models.User;
import com.inn.cafe2.com.inn.cafe2.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/user")
public interface UserRest {
    @PostMapping("/signup")
    public ResponseEntity<String>signUP(@RequestBody Map<String,String>requestMapping);
    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody Map<String,String>requestMapping);
    @GetMapping(path = "/get")
    public ResponseEntity<List<UserWrapper>> getAllUser();
    @PostMapping(path = "/update")
    public ResponseEntity<String>update(@RequestBody Map<String,String>requestMap);
}
