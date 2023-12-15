package com.example.pattern.category;

import com.example.pattern.book.dtos.BookViewDto;
import com.example.pattern.category.dtos.CategoryViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/add-category")
    public String addCategory(){
        return "add-category";
    }

    @PostMapping("/categories/add")
    public String addCategory(@RequestParam("categoryName") String categoryName,
                              RedirectAttributes redirectAttributes) {
        if(categoryName.isBlank()){
            return "add-category";
        }

        categoryService.addCategory(categoryName);
        redirectAttributes.addFlashAttribute("successMessage", "Category added successfully!");
        return "redirect:/dashboard/categories";
    }

    @PostMapping("/categories/{categoryId}/delete")
    public String deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return "redirect:/dashboard/categories";
    }

    @GetMapping("/dashboard/categories")
    public String getCategoriesDashboard(Model model){
        List<CategoryViewDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories-table";
    }
}
