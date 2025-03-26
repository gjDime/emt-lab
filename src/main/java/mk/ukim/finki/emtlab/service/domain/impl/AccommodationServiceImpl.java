package mk.ukim.finki.emtlab.service.domain.impl;

import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.repository.AccommodationRepository;
import mk.ukim.finki.emtlab.service.domain.AccommodationService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
    }


    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getHost() != null &&
                hostService.findById(accommodation.getHost().getId()).isPresent()) {
            return Optional.of(
                    accommodationRepository.save(new Accommodation(
                            accommodation.getName(),
                            accommodation.getNumberOfRooms(),
                            hostService.findById(accommodation.getHost().getId()).get(),
                            accommodation.getCategory())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id).map(acc -> {

            if (accommodation.getName() != null)
                acc.setName(accommodation.getName());

            if (accommodation.getNumberOfRooms() != null)
                acc.setNumberOfRooms(accommodation.getNumberOfRooms());

            if (accommodation.getHost() != null &&
                    hostService.findById(accommodation.getHost().getId()).isPresent()) {
                acc.setHost(hostService.findById(accommodation.getHost().getId()).get());
            }

            if (accommodation.getCategory() != null) {
                acc.setCategory(accommodation.getCategory());
            }

            return accommodationRepository.save(acc);
        });
    }

    @Override
    public void deleteById(long id) {
        accommodationRepository.deleteById(id);
    }
}
