package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        //Make a call to DB to fetch a product with given id.

        Optional<Product> productOptional= productRepository.findById(productId);

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product  with id "+productId+" doesn't exist");
        }

        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    //PATCH
    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with given id : "+id+ "doesn't exist");
        }
        Product productInDB = productOptional.get();

        if(product.getTitle()!= null) {
            productInDB.setTitle(product.getTitle());
        }

        if(product.getPrice()!= null)
        {
            productInDB.setPrice(product.getPrice());
        }
        return productRepository.save(productInDB);

    }
    //PUT
    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
         productRepository.deleteById(id);
    }

    @Override
    public Product addNewProduct(Product product) {
        Category category =product.getCategory();

//        if(category.getId()==null)
//        {
//            // We need to create a new category object in DB first
//            category = categoryRepository.save(category);
//            product.setCategory(category);
//        }
        return productRepository.save(product);
    }
}
