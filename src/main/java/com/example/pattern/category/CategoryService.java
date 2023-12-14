package com.example.pattern.category;

import com.example.pattern.category.dtos.CategoryViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public void addCategory(String categoryName){
        Category category = new Category();
        category.setName(categoryName);
        categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId){
        boolean exist = categoryRepository.existsById(categoryId);
        if(!exist){
            throw new NoSuchElementException();
        }
        categoryRepository.deleteById(categoryId);
    }

    public List<CategoryViewDto> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::mapToCategoryViewDto).collect(Collectors.toList());
    }
}
