package mk.ukim.finki.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtlab.dto.CreateCountryDto;
import mk.ukim.finki.emtlab.dto.DisplayCountryDto;
import mk.ukim.finki.emtlab.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
@Tag(name = "Country", description = "Endpoints for managing countries")
public class CountryController {
    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryService) {
        this.countryApplicationService = countryService;
    }

    @Operation(
            summary = "Get all countries",
            description = "Retrieve a list of all countries.",
            tags = {"Country"}
    )
    @GetMapping
    public List<DisplayCountryDto> getAll() {
        return countryApplicationService.findAll();
    }

    @Operation(
            summary = "Get country by ID",
            description = "Retrieve details of a specific country by its ID.",
            tags = {"Country"}
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryApplicationService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new country",
            description = "Create a new country with the provided details.",
            tags = {"Country"}
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto country) {
        return countryApplicationService.save(country)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Update country",
            description = "Update an existing country by its ID.",
            tags = {"Country"}
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto country) {
        return countryApplicationService.update(id, country)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete country",
            description = "Delete a country by its ID.",
            tags = {"Country"}
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (countryApplicationService.findById(id).isPresent()) {
            countryApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
