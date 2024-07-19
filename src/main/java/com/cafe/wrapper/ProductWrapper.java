package com.cafe.wrapper;

import lombok.Data;

@Data
public class ProductWrapper {

  Integer id;
  String productName;
  String description;
  Integer price;
  String status;
  Integer categoryId;
  String categoryName;

  public ProductWrapper() {}

  public ProductWrapper(Integer id, String productName, String description, Integer price, String status, Integer categoryId, String categoryName) {
    this.id = id;
    this.productName = productName;
    this.description = description;
    this.price = price;
    this.status = status;
    this.categoryId = categoryId;
    this.categoryName = categoryName;
  }

  public ProductWrapper(Integer id, String productName, String description, Integer price, String categoryName) {
    this.id = id;
    this.productName = productName;
    this.description = description;
    this.price = price;
    this.categoryName = categoryName;
  }

}
