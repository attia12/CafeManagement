package com.inn.cafe2.com.inn.cafe2.restIMPL;

import com.inn.cafe2.com.inn.cafe2.constents.CafeConstants;
import com.inn.cafe2.com.inn.cafe2.dao.UserDAO;
import com.inn.cafe2.com.inn.cafe2.rest.UserRest;
import com.inn.cafe2.com.inn.cafe2.service.UserService;
import com.inn.cafe2.com.inn.cafe2.utils.CafeUtils;
import com.inn.cafe2.com.inn.cafe2.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController

public class UserRestIMPL implements UserRest {
    @Autowired
    UserService userService;
    @Autowired
    private UserDAO userDAO;

    @Override
    public ResponseEntity<String> signUP(Map<String, String> requestMapping) {
        try
        {
            return userService.signUp(requestMapping);

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
          return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMapping) {
        try {
            return userService.login(requestMapping);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {

            return userService.getAllUser();
        }catch (Exception ex){ ex.printStackTrace();}
        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try
        {
            return userService.update(requestMap);



        }catch (Exception ex)
        {ex.printStackTrace();}
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
