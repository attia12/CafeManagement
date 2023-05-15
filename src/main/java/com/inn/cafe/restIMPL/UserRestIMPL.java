package com.inn.cafe.restIMPL;

import com.inn.cafe.constents.CafeConstants;
import com.inn.cafe.rest.UserRest;
import com.inn.cafe.service.UserService;
import com.inn.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class UserRestIMPL implements UserRest {
    @Autowired
    UserService userService;
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
}
