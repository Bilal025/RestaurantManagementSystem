package com.cafe.restimpl;

import com.cafe.constent.CafeConstant;
import com.cafe.rest.CategoryRest;
import com.cafe.rest.ProductRest;
import com.cafe.service.ProductService;
import com.cafe.serviceimpl.ProductServiceImpl;
import com.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CategoryRestImpl implements CategoryRest {

    @Override
    public ResponseEntity<String> addCategory(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<List<String>> getAllCategory(String filterValue) {
        return null;
    }


}
