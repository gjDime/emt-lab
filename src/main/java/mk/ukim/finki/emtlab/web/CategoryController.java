package mk.ukim.finki.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtlab.model.enumerations.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@Tag(name = "Category", description = "Endpoints for retrieving accommodation categories")
public class CategoryController {

    @Operation(
            summary = "Get all categories",
            description = "Retrieve a list of all available accommodation categories.",
            tags = {"Category"}
    )
    @GetMapping
    public List<Category> getCategories() {
        return Arrays.stream(Category.values()).toList();
    }
}
