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
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryService service;


    public Character addProduct(Product product){
        for (Category category: service.getCategories()) {
            if (category.getId() == product.getCategoryID()){
                product.setCategoryID(category.getId());
            }
        }

        productRepository.save(product);
        return 'A';

    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Character updateProduct(Integer id, Product product){
        Product product1 = productRepository.getById(id);
        for (Product product11: productRepository.findAll() ) {
            if (product11.getId() == product.getId()){
                product1.setName(product11.getName());
                product1.setPrice(product11.getPrice());
                productRepository.save(product1);
                return 'A';
            }
        }
        return 'F';
    }

    public Character deleteProduct(Integer id){
        Product product = productRepository.getById(id);
        for (Product product1 : productRepository.findAll()) {
            if (product1.getId() == id){
                productRepository.delete(product);
                return 'A';
            }
        }
        return 'F';
    }



}
