package mk.ukim.finki.emtlab.service.domain;

import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.ReservedAccommodation;

import java.util.Optional;

public interface ReservedAccommodationService {
    Optional<ReservedAccommodation> reserveAccommodation(Long accommodationId, String userId);

    Optional<Accommodation> freeAccommodation(Long accommodationId);
}
