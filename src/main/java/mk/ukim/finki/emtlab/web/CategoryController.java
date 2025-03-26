package mk.ukim.finki.emtlab.web;

import mk.ukim.finki.emtlab.model.enumerations.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @GetMapping
    public List<Category> getCategories() {
        return Arrays.stream(Category.values()).toList();
    }
}
