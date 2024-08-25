package com.scaler.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {

    private String name;
    private String description;
    @OneToMany(mappedBy = "category") //mappedBy--we have already defined relation between category & product in product class.
    // so you don't have to persist this change in db. this is only for code purpose. no lookup table will be created
    private List<Product> products;
}
