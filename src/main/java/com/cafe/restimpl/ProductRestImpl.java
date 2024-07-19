package com.cafe.restimpl;

import com.cafe.constent.CafeConstant;
import com.cafe.rest.ProductRest;
import com.cafe.service.ProductService;
import com.cafe.utils.CafeUtils;
import com.cafe.wrapper.ProductWrapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProductRestImpl implements ProductRest {

  @Autowired
  ProductService productService;

  @Override
  public ResponseEntity<String> addNewProductAdd(Map<String, String> requestMap) {
    try {
      return productService.addNewProduct(requestMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return CafeUtils.getResponseEntity(CafeConstant.SOMETHING_WENT_WRONG,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<List<ProductWrapper>> getAllProduct() {

    try {
      return productService.getAllProduct();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
    try {
      return productService.updateProduct(requestMap);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return CafeUtils.getResponseEntity(CafeConstant.SOMETHING_WENT_WRONG,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<String> deleteProduct(Integer id) {
    try {
      return productService.deleteProduct(id);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return CafeUtils.getResponseEntity(CafeConstant.SOMETHING_WENT_WRONG,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<String> updateProductStatus(Map<String, String> requestMap) {
    try {
      return productService.updateProductStatus(requestMap);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return CafeUtils.getResponseEntity(CafeConstant.SOMETHING_WENT_WRONG,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<List<ProductWrapper>> getProductByCategory(Integer id) {
    try {
      return productService.getProductByCategory(id);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<ProductWrapper> getProductById(Integer id) {
    try{
      return productService.getProductById(id);

    } catch (Exception e){
      e.printStackTrace();
    }

    return new ResponseEntity<>(new ProductWrapper() , HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
