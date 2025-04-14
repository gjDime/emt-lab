package mk.ukim.finki.emtlab.web;

import mk.ukim.finki.emtlab.model.domain.Guest;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.service.domain.GuestService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guest")
public class GuestController {
    private final GuestService guestService;
    private final HostService hostService;

    public GuestController(GuestService guestService, HostService hostService) {
        this.guestService = guestService;
        this.hostService = hostService;
    }

    @GetMapping
    public List<Guest> getAll() {
        return guestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> findById(@PathVariable Long id) {
        return guestService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addguest")
    public Optional<Guest> addHost(@RequestBody Guest guest, @RequestBody Host host) {
        return guestService.addHost(guest,host);
    }

    @PostMapping("/add")
    public ResponseEntity<Guest> save(@RequestBody Guest guest) {
        return guestService.save(guest)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Guest> update(@PathVariable Long id, @RequestBody Guest guest) {
        return guestService.update(id, guest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

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
