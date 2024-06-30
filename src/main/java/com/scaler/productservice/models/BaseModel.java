package com.scaler.productservice.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class BaseModel {
    private long id;
    private Date createdAt;
    private Date updatedAt;
}
