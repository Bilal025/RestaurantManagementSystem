package com.cafe.serviceimpl;

import com.cafe.JWT.JwtFilter;
import com.cafe.constent.CafeConstant;
import com.cafe.dao.CategoryDao;
import com.cafe.model.Category;
import com.cafe.service.CategoryService;
import com.cafe.utils.CafeUtils;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  CategoryDao categoryDao;

  @Autowired
  JwtFilter jwtFilter;


  //? Add New Category function ---------------------------
  @Override
  public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
    try {
      if (jwtFilter.isAdmin()) {
        if (validateCategoryMap(requestMap, false)) {
          categoryDao.save(getCategoryFromMap(requestMap, false));
          return CafeUtils.getResponseEntity("Category added successfully", HttpStatus.OK);
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

  //? Get all category list ---------------------------
  @Override
  public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
    try {
      if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
        return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(), HttpStatus.OK);
      }
      return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  //? Update category ---------------------------
  @Override
  public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
    try {
      if (jwtFilter.isAdmin()) {
        if (validateCategoryMap(requestMap, true)) {
          Optional optional = categoryDao.findById(Integer.parseInt(requestMap.get("id")));
          if (!optional.isEmpty()) {
            categoryDao.save(getCategoryFromMap(requestMap, true));
            return CafeUtils.getResponseEntity("Category updated successfully", HttpStatus.OK);
          } else {
            return CafeUtils.getResponseEntity("Category id doesn't Exist", HttpStatus.OK);
          }
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

  //? Other functions - Add Category ---------------------------
  private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
    if (requestMap.containsKey("categoryname")) {
      if (requestMap.containsKey("id") && validateId) {
        return true;
      } else if (!validateId) {
        return true;
      }
    }
    return false;
  }

  private Category getCategoryFromMap(Map<String, String> requestMap, Boolean isAdd) {
    Category category = new Category();
    if (isAdd) {
      category.setId(Integer.parseInt(requestMap.get("id")));
    }
    category.setCategoryName(requestMap.get("categoryname"));
    return category;
  }
}
