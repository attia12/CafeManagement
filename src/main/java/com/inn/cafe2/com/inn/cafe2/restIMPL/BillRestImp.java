package com.inn.cafe2.com.inn.cafe2.restIMPL;

import com.inn.cafe2.com.inn.cafe2.constents.CafeConstants;
import com.inn.cafe2.com.inn.cafe2.dao.BillDao;
import com.inn.cafe2.com.inn.cafe2.rest.BillRest;
import com.inn.cafe2.com.inn.cafe2.service.BillService;
import com.inn.cafe2.com.inn.cafe2.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class BillRestImp implements BillRest {
    @Autowired
    BillService billService;


    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        try {
            return billService.generateReport(requestMap);
        }catch (Exception ex)
        {ex.printStackTrace();}
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
