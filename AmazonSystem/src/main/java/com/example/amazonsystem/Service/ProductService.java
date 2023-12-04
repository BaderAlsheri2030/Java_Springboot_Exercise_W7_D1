package com.example.amazonsystem.Service;

import com.example.amazonsystem.Model.Category;
import com.example.amazonsystem.Model.Product;
import com.example.amazonsystem.Repository.CategoryRepository;
import com.example.amazonsystem.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final ProductRepository productRepository;


    public void addProduct(Integer category_id,Product product){
        Category category = categoryRepository.getById(category_id);
        Product product1 = new Product();
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setCategory(category);
        productRepository.save(product1);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public boolean updateProduct(Integer id, Product product){
        Product product1 = productRepository.getById(id);
        if (product1 ==null)
        return false;
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());

        productRepository.save(product1);
        return true;
    }

    public boolean deleteProduct(Integer id){
       Product product = productRepository.getById(id);
       if (product == null)
        return false;

       productRepository.delete(product);
       return true;
    }



}
