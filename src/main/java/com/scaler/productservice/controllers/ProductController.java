package com.scaler.productservice.controllers;

import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("selfProductService")
                             ProductService productService)
    {
        this.productService=productService;
    }

    // http://localhost:8080/products/10

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
//        ResponseEntity<Product> responseEntity =null;
//        try {
//            Product product= productService.getSingleProduct(id);
//            responseEntity =new ResponseEntity<>(product, HttpStatus.OK);
//        }
//        catch (RuntimeException e)
//        {
//            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        ResponseEntity<Product> response= new ResponseEntity<>(productService.getSingleProduct(id),HttpStatus.OK);
        return response;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);

    }

    //PATCH --> http://localhost:8080/products/1
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody Product product){
        return  null;
    }

//    @ExceptionHandler(ArithmeticException.class)
//    public ResponseEntity<String> handleArithmeticException()
//    {
//        ResponseEntity<String> response= new ResponseEntity<>(
//                "Arithmetic Exception has happened, Inside the controller", HttpStatus.BAD_REQUEST);
//        return response;
//    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product){
        return  productService.addNewProduct(product);
    }
}

