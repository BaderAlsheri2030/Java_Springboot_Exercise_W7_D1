package com.example.amazonsystem.Service;

import com.example.amazonsystem.Model.Category;
import com.example.amazonsystem.Repository.CategoryRepository;
import com.example.amazonsystem.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public boolean updateCategory(Integer id , Category category){
        Category category1 = categoryRepository.getById(id);
        if (category1 == null){
            return false;
        }
        category1.setName(category.getName());
        category1.setId(category.getId());
        categoryRepository.save(category);
        return true;
    }


    public boolean deleteCategory(Integer id){
      Category category = categoryRepository.getById(id);
      if (category == null){
          return false;
      }
      categoryRepository.delete(category);
        return true;
    }
}
