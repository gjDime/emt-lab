package mk.ukim.finki.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtlab.dto.AccommodationDetailsDto;
import mk.ukim.finki.emtlab.dto.CreateAccommodationDto;
import mk.ukim.finki.emtlab.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlab.model.views.AccommodationsPerHostView;
import mk.ukim.finki.emtlab.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodation")
@Tag(name = "Accommodation", description = "Endpoints for managing accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @Operation(
            summary = "Get all accommodations",
            description = "Retrieve a list of all accommodations.",
            tags = {"Accommodation"}
    )
    @GetMapping
    public List<DisplayAccommodationDto> findAll() {
        return accommodationApplicationService.findAll();
    }

    @Operation(
            summary = "Get accommodation by ID",
            description = "Retrieve details of a specific accommodation by its ID.",
            tags = {"Accommodation"}
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return accommodationApplicationService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new accommodation",
            description = "Create a new accommodation with the provided details.",
            tags = {"Accommodation"}
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto accommodation) {
        return accommodationApplicationService.save(accommodation)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Update accommodation",
            description = "Update an existing accommodation by its ID.",
            tags = {"Accommodation"}
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id, @RequestBody CreateAccommodationDto accommodation) {
        return accommodationApplicationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete accommodation",
            description = "Delete an accommodation by its ID.",
            tags = {"Accommodation"}
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (accommodationApplicationService.findById(id).isPresent()) {
            accommodationApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Get accommodations per host",
            description = "Retrieve a list of accommodations grouped by host.",
            tags = {"Accommodation"}
    )
    @GetMapping("/by-host")
    public ResponseEntity<List<AccommodationsPerHostView>> accommodationsPerHost() {
        return ResponseEntity.ok().body(this.accommodationApplicationService.findAllPerHost());
    }

    @Operation(
            summary = "Get accommodation details",
            description = "Retrieve detailed information for a specific accommodation by its ID.",
            tags = {"Accommodation"}
    )
    @PostMapping("{id}/details")
    public ResponseEntity<AccommodationDetailsDto> getAccommodationDetails(@PathVariable Long id) {
        return accommodationApplicationService.findDetailsById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
