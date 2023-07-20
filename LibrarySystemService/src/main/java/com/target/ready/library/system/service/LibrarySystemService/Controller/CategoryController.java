package com.target.ready.library.system.service.LibrarySystemService.Controller;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("library_service_api/v2")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("inventory/category")
    public String addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/category/{categoryName}")
    public Category findByCategoryName(@PathVariable String categoryName){
        return categoryService.findByCategoryName(categoryName);
    }
}
