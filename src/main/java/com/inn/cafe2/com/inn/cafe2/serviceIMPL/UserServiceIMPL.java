package com.inn.cafe2.com.inn.cafe2.serviceIMPL;

import com.inn.cafe2.com.inn.cafe2.constents.CafeConstants;
import com.inn.cafe2.com.inn.cafe2.dao.UserDAO;
import com.inn.cafe2.com.inn.cafe2.models.User;
import com.inn.cafe2.com.inn.cafe2.service.UserService;
import com.inn.cafe2.com.inn.cafe2.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j //for logging purpose
@Service

public class UserServiceIMPL implements UserService {
    @Autowired
    UserDAO userDAO;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("inside signup {}",requestMap);
        try {
            if(validateSignUpMap(requestMap))

            {
                User user=userDAO.findByEmailId(requestMap.get("email"));
                if(Objects.isNull(user))
                {

                    userDAO.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity("Successfully Registered.",HttpStatus.OK);
                }
                else
                {
                    return CafeUtils.getResponseEntity("Email already exist",HttpStatus.BAD_REQUEST);
                }


            }
            else {
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex){
            ex.printStackTrace();



        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);


    }
    private boolean validateSignUpMap(Map<String, String> requestMap)
    {
        if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")&& requestMap.containsKey("email")&& requestMap.containsKey("password"))
        {
            return true;
        }
        return false;

    }
    private User getUserFromMap(Map<String,String> requestMap)

    {
            User user=new User();
            user.setName(requestMap.get("name"));
            user.setContactNumber(requestMap.get("contactNumber"));
            user.setEmail(requestMap.get("email"));
            user.setPassword(requestMap.get("password"));
            user.setStatus("false");
            user.setRole("user");
            return user;
    }
}
