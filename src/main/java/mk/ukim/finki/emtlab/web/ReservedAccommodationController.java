package mk.ukim.finki.emtlab.web;

import mk.ukim.finki.emtlab.dto.DisplayGuestDto;
import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.ReservedAccommodation;
import mk.ukim.finki.emtlab.repository.ReservedAccommodationRepository;
import mk.ukim.finki.emtlab.service.domain.ReservedAccommodationService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reserve")
public class ReservedAccommodationController {
    private final ReservedAccommodationService reservedAccommodationService;

    public ReservedAccommodationController(ReservedAccommodationService reservedAccommodationService) {
        this.reservedAccommodationService = reservedAccommodationService;
    }

    @PostMapping("/add")
    public Optional<ReservedAccommodation> reserve(@RequestParam Long accommodationId, @RequestParam String userId) {

        return reservedAccommodationService.reserveAccommodation(accommodationId, userId);
    }

    @PostMapping("/clear/{id}")
    public Optional<Accommodation> reserve(@PathVariable Long id) {

        return reservedAccommodationService.freeAccommodation(id);
    }
}
