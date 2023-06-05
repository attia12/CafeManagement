package com.inn.cafe2.com.inn.cafe2.serviceIMPL;

import com.inn.cafe2.com.inn.cafe2.JWT.CustumerUserDetailsService;
import com.inn.cafe2.com.inn.cafe2.JWT.JwtFilter;
import com.inn.cafe2.com.inn.cafe2.JWT.JwtUtil;
import com.inn.cafe2.com.inn.cafe2.constents.CafeConstants;
import com.inn.cafe2.com.inn.cafe2.dao.UserDAO;
import com.inn.cafe2.com.inn.cafe2.models.User;
import com.inn.cafe2.com.inn.cafe2.service.UserService;
import com.inn.cafe2.com.inn.cafe2.utils.CafeUtils;
import com.inn.cafe2.com.inn.cafe2.utils.EmailUtils;
import com.inn.cafe2.com.inn.cafe2.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j //for logging purpose
@Service

public class UserServiceIMPL implements UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustumerUserDetailsService custumerUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    EmailUtils emailUtils;
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

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try {

            //authenticate user
            Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password")));

            if(auth.isAuthenticated())
            {
                if(custumerUserDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) //user approved by admin
                {
                    log.info("login user :" ,custumerUserDetailsService.getUserDetail() );

                    return new ResponseEntity<String>("{\"token\":\""+ jwtUtil.generateToken(custumerUserDetailsService.getUserDetail().getEmail(),custumerUserDetailsService.getUserDetail().getRole()) + "\"}",HttpStatus.OK)  ;              //return the token
                }
                else
                {
                    return new ResponseEntity<String>("{\"message\" :\""+"wait for admin approvel"+"\"}",HttpStatus.BAD_REQUEST);
                }


            }
        }catch (Exception ex)
        {
            log.error("{}",ex);
        }

        return new ResponseEntity<String>("{\"message\" :\""+"Bad Credentiel"+"\"}",HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {
            if(jwtFilter.isAdmin())
            {
                return new ResponseEntity<>(userDAO.getAllUser(),HttpStatus.OK);

            }else {


                return new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception ex)
        {ex.printStackTrace();}
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try{
            //check admin
            if(jwtFilter.isAdmin())
            {
                //search the user exist in our data base or no
                Optional<User> optionalUser=userDAO.findById(Integer.parseInt(requestMap.get("id")));
                if(!optionalUser.isEmpty())
                {
                    userDAO.updateStatus(requestMap.get("status"),Integer.parseInt(requestMap.get("id")));
                    //log.info(optionalUser.get().getEmail());
                    //sending email
                    sendMailToAllAdmin(requestMap.get("status"),optionalUser.get().getEmail(),userDAO.getAllAdmin());
                    return CafeUtils.getResponseEntity("User status updated succesfully",HttpStatus.OK);
                }
                else {
                    return CafeUtils.getResponseEntity("User id doesn't exist",HttpStatus.OK);


                }


            }
            else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS,HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception ex)
        {ex.printStackTrace();}
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
        allAdmin.remove(jwtFilter.getCurrentUser());
        if(status != null && status.equalsIgnoreCase("true"))
        {

            //send enable message
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Approved","USER:-"+user+"\n is approved by \nADMIN:-"+jwtFilter.getCurrentUser(),allAdmin);
        }
        else {

            //send the disabled message
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Disable","USER:-"+user+"\n is disabled by \nADMIN:-"+jwtFilter.getCurrentUser(),allAdmin);
        }
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
