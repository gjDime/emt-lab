package mk.ukim.finki.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtlab.dto.CreateHostDto;
import mk.ukim.finki.emtlab.dto.DisplayHostDto;
import mk.ukim.finki.emtlab.model.projections.HostProjection;
import mk.ukim.finki.emtlab.model.views.HostsPerCountryView;
import mk.ukim.finki.emtlab.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
@Tag(name = "Host", description = "Endpoints for managing hosts and host-related queries")
public class HostController {
    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @Operation(
            summary = "Get all hosts",
            description = "Retrieve a list of all hosts.",
            tags = {"Host"}
    )
    @GetMapping
    public List<DisplayHostDto> findAll() {
        return hostApplicationService.findAll();
    }

    @Operation(
            summary = "Get host by ID",
            description = "Retrieve details of a specific host by their ID.",
            tags = {"Host"}
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostApplicationService.findById(id)
                .map(h -> ResponseEntity.ok().body(h))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new host",
            description = "Create a new host with the provided details.",
            tags = {"Host"}
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto host) {
        return hostApplicationService.save(host)
                .map(h -> ResponseEntity.ok().body(h))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Update host",
            description = "Update an existing host by their ID.",
            tags = {"Host"}
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto host) {
        return hostApplicationService.update(id, host)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete host",
            description = "Delete a host by their ID.",
            tags = {"Host"}
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (hostApplicationService.findById(id).isPresent()) {
            hostApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Get names and surnames of all hosts",
            description = "Retrieve a list of all hosts' names and surnames.",
            tags = {"Host"}
    )
    @GetMapping("/names")
    public ResponseEntity<List<HostProjection>> getNameAndSurnameOfHosts() {
        return ResponseEntity.ok().body(hostApplicationService.takeNameAndSurnameByProjection());
    }

    @Operation(
            summary = "Get hosts per country",
            description = "Retrieve a list of hosts grouped by country.",
            tags = {"Host"}
    )
    @GetMapping("/by-contry")
    public ResponseEntity<List<HostsPerCountryView>> hostsPerCountry() {
        return ResponseEntity.ok().body(this.hostApplicationService.findAllPerCountry());
    }
}
