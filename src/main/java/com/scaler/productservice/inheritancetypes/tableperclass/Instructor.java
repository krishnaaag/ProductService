package com.scaler.productservice.inheritancetypes.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_instructors")
public class Instructor extends User {
    private String subject;
}
