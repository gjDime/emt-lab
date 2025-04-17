package mk.ukim.finki.emtlab.service.domain.impl;

import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.ReservedAccommodation;
import mk.ukim.finki.emtlab.model.exceptions.AccomodationAlreadyRentedException;
import mk.ukim.finki.emtlab.repository.ReservedAccommodationRepository;
import mk.ukim.finki.emtlab.service.domain.AccommodationService;
import mk.ukim.finki.emtlab.service.domain.ReservedAccommodationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservedAccommodationServiceImpl implements ReservedAccommodationService {
    private final ReservedAccommodationRepository reservedAccommodationRepository;
    private final AccommodationService accommodationService;

    public ReservedAccommodationServiceImpl(ReservedAccommodationRepository reservedAccommodationRepository, AccommodationService accommodationService) {
        this.reservedAccommodationRepository = reservedAccommodationRepository;
        this.accommodationService = accommodationService;
    }


    //TODO
    @Override
    public Optional<ReservedAccommodation> reserveAccommodation(Long accommodationId, String userId) {
        var acc = accommodationService.findById(accommodationId);
        if (acc.isEmpty() || acc.get().getRented()) {
            throw new AccomodationAlreadyRentedException();
        }
        acc.get().setRented(Boolean.TRUE);

        ReservedAccommodation reserved = new ReservedAccommodation(accommodationId, userId);
        return Optional.of(reservedAccommodationRepository.save(reserved));
    }

    @Override
    public Optional<Accommodation> freeAccommodation(Long accommodationId) {
        Optional<Accommodation> acc = accommodationService.findById(accommodationId);

        if (acc.isPresent()) {
            acc.get().setRented(Boolean.FALSE);
            return accommodationService.save(acc.get());
        }

        return Optional.empty();
    }
}
