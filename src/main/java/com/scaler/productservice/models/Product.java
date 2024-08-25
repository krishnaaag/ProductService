package com.scaler.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private Double price;
    @ManyToOne(cascade = CascadeType.PERSIST) // when we try to save product object via POST then automatically persist(save) the category id object as well
    private Category category;
  }

  /*

  Product ----------- Category => M:1
     M                   1

    */






