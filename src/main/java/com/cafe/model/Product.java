package com.cafe.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NamedQuery(name = "Product.getAllProduct", query = "select new com.cafe.wrapper.ProductWrapper(p.id,p.productName,p.description,p.price,p.status,p.category.id,p.category.categoryName) from Product p")

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
