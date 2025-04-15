package mk.ukim.finki.emtlab.web;

import mk.ukim.finki.emtlab.dto.CreateGuestDto;
import mk.ukim.finki.emtlab.dto.CreateHostDto;
import mk.ukim.finki.emtlab.dto.DisplayGuestDto;
import mk.ukim.finki.emtlab.model.domain.Guest;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.service.application.GuestApplicationService;
import mk.ukim.finki.emtlab.service.application.HostApplicationService;
import mk.ukim.finki.emtlab.service.domain.GuestService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guest")
public class GuestController {
    private final GuestApplicationService guestService;
    private final HostApplicationService hostService;

    public GuestController(GuestApplicationService guestService, HostApplicationService hostService) {
        this.guestService = guestService;
        this.hostService = hostService;
    }


    @GetMapping
    public List<DisplayGuestDto> getAll() {
        return guestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayGuestDto> findById(@PathVariable Long id) {
        return guestService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addguest")
    public Optional<DisplayGuestDto> addHost(@RequestParam Long guestId, @RequestParam Long hostId) {
        return guestService.addHost(guestId,hostId);
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayGuestDto> save(@RequestBody CreateGuestDto guest) {
        return guestService.save(guest)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayGuestDto> update(@PathVariable Long id, @RequestBody CreateGuestDto guest) {
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
