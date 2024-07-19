package com.cafe.dao;

import com.cafe.model.Product;
import com.cafe.wrapper.ProductWrapper;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

public interface ProductDao extends JpaRepository<Product, Integer> {

  List<ProductWrapper> getAllProduct();

  @Modifying
  @Transactional
  Integer updateProductStatus(@Param("status")String status, @Param("pid") Integer id);

  List<ProductWrapper> getProductByCategory(@Param("id") Integer id);

  ProductWrapper getProductById(@Param("id") Integer id);
}
