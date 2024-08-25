package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product) throws ProductNotFoundException;

    Product replaceProduct(Long id, Product product);

    void  deleteProduct(Long id);

    Product addNewProduct(Product product);



}
