package com.scaler.productservice.services;

import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service

public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public  FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate=restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {

        // call ProductService to fetch the product with given id => HTTP Call
       FakeStoreProductDto fakeStoreProductDto= restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);

       //Convert FakeStoreProductDto into Product
        return convertFakeStoreProductToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos= restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);

        //Convert List of FakeStoreProductDtos into List of Product
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos)
        {
            products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
        }
        return products;
    }

    //Partial update
    //Response type- .class
    //We got these lines from restTemplate.patchForObject as they have no return type that's why we used lines where return mentioned

    @Override
    public Product updateProduct(Long id, Product product) {
        //PATCH - Input object - Product
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response= restTemplate.execute("https://fakestoreapi.com/products/"+ id, HttpMethod.PATCH, requestCallback, responseExtractor);

        //Convert FakeStoreDTO to product
        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        //PUT

        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitles());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(category);

        return product;
    }
}
