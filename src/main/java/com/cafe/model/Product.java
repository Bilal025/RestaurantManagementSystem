package com.cafe.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NamedQuery(name = "Product.getAllProduct", query = "select new com.cafe.wrapper.ProductWrapper(p.id,p.productName,p.description,p.price,p.status,p.category.id,p.category.categoryName) from Product p")

@NamedQuery(name = "Product.updateProductStatus", query = "update Product p set p.status=:status where p.id=:pid")

@NamedQuery(name = "Product.getProductByCategory", query = "select new com.cafe.wrapper.ProductWrapper(p.id,p.productName,p.description,p.price,p.category.categoryName) from Product p where p.category.id=:id and p.status='true'")

@NamedQuery(name = "Product.getProductById", query = "select new com.cafe.wrapper.ProductWrapper(p.id,p.productName,p.description,p.price,p.category.categoryName) from Product p where p.id=:id")

@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pid")
  private Integer id;

  @Column(name = "productname")
  private String productName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_fk", nullable = false)
  private Category category;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private Integer price;

  @Column(name = "status")
  private String status;

}
