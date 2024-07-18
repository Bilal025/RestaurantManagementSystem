package com.cafe.serviceimpl;

import com.cafe.JWT.JwtFilter;
import com.cafe.constent.CafeConstant;
import com.cafe.dao.ProductDao;
import com.cafe.model.Category;
import com.cafe.model.Product;
import com.cafe.service.ProductService;
import com.cafe.utils.CafeUtils;
import com.cafe.wrapper.ProductWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  ProductDao productDao;

  @Autowired
  JwtFilter jwtFilter;

  //? Add product --------------------------------------------------------
  @Override
  public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
    try {
      if (jwtFilter.isAdmin()) {
        if (validateProductMap(requestMap, false)) {
          productDao.save(getProductFromMap(requestMap, false));
          return CafeUtils.getResponseEntity("Product added successfully", HttpStatus.OK);
        }
        return CafeUtils.getResponseEntity(CafeConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);

      } else {
        return CafeUtils.getResponseEntity(CafeConstant.UNAUTHORIZED_ACCESS,
            HttpStatus.UNAUTHORIZED);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return CafeUtils.getResponseEntity(CafeConstant.SOMETHING_WENT_WRONG,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  //? Get all product --------------------------------------------------------

  @Override
  public ResponseEntity<List<ProductWrapper>> getAllProduct() {
    try {

      return new ResponseEntity<>(productDao.getAllProduct(), HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  //? Update product --------------------------------------------------------

  @Override
  public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
    try {
      if (jwtFilter.isAdmin()) {
        if (validateProductMap(requestMap, true)) {
          Optional<Product> optional = productDao.findById(Integer.parseInt(requestMap.get("pid")));
          if (!optional.isEmpty()) {
            Product product = getProductFromMap(requestMap, true);
            product.setStatus(optional.get().getStatus());
            productDao.save(product);

            return CafeUtils.getResponseEntity("Product updated successfully", HttpStatus.OK);

          } else {
            return CafeUtils.getResponseEntity("Product id does not exist", HttpStatus.OK);
          }

        }
      } else {
        return CafeUtils.getResponseEntity(CafeConstant.UNAUTHORIZED_ACCESS,
            HttpStatus.UNAUTHORIZED);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return CafeUtils.getResponseEntity(CafeConstant.SOMETHING_WENT_WRONG,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }


  //? Helper function - Add product --------------------------------------------------------
  private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
    if (requestMap.containsKey("productname")) {
      if (requestMap.containsKey("pid") && validateId) {
        return true;

      } else if (!validateId) {
        return true;
      }
    }
    return false;
  }

  //? Helper function - save product --------------------------------------------------------
  private Product getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
    Category category = new Category();
    category.setId(Integer.parseInt(requestMap.get("categoryid")));

    Product product = new Product();

    if (isAdd) {
      product.setId(Integer.parseInt(requestMap.get("pid")));
    } else {
      product.setStatus("true");
    }
    product.setCategory(category);
    product.setProductName(requestMap.get("productname"));
    product.setDescription(requestMap.get("description"));
    product.setPrice(Integer.parseInt(requestMap.get("price")));

    return product;
  }
}
