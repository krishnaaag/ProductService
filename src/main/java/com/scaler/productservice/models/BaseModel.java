package com.scaler.productservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO INCREMENT
    private long id;
    private Date createdAt;
    private Date updatedAt;
}
