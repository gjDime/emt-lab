package mk.ukim.finki.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtlab.dto.CreateGuestDto;
import mk.ukim.finki.emtlab.dto.DisplayGuestDto;
import mk.ukim.finki.emtlab.service.application.GuestApplicationService;
import mk.ukim.finki.emtlab.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guest")
@Tag(name = "Guest", description = "Endpoints for managing guests and their relationships with hosts")
public class GuestController {
    private final GuestApplicationService guestService;
    private final HostApplicationService hostService;

    public GuestController(GuestApplicationService guestService, HostApplicationService hostService) {
        this.guestService = guestService;
        this.hostService = hostService;
    }

    @Operation(
            summary = "Get all guests",
            description = "Retrieve a list of all guests.",
            tags = {"Guest"}
    )
    @GetMapping
    public List<DisplayGuestDto> getAll() {
        return guestService.findAll();
    }

    @Operation(
            summary = "Get guest by ID",
            description = "Retrieve details of a specific guest by their ID.",
            tags = {"Guest"}
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisplayGuestDto> findById(@PathVariable Long id) {
        return guestService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add host to guest",
            description = "Assign a host to a guest by their IDs.",
            tags = {"Guest"}
    )
    @PostMapping("/addguest")
    public Optional<DisplayGuestDto> addHost(@RequestParam Long guestId, @RequestParam Long hostId) {
        return guestService.addHost(guestId, hostId);
    }

    @Operation(
            summary = "Create a new guest",
            description = "Add a new guest with the provided details.",
            tags = {"Guest"}
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayGuestDto> save(@RequestBody CreateGuestDto guest) {
        return guestService.save(guest)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Update guest",
            description = "Update an existing guest by their ID.",
            tags = {"Guest"}
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayGuestDto> update(@PathVariable Long id, @RequestBody CreateGuestDto guest) {
        return guestService.update(id, guest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete guest",
            description = "Delete a guest by their ID.",
            tags = {"Guest"}
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (guestService.findById(id).isPresent()) {
            guestService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
