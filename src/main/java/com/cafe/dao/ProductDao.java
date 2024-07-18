package com.cafe.dao;

import com.cafe.model.Product;
import com.cafe.wrapper.ProductWrapper;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {

  List<ProductWrapper> getAllProduct();
}
