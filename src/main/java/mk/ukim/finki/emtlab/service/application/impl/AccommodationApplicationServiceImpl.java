package mk.ukim.finki.emtlab.service.application.impl;

import mk.ukim.finki.emtlab.dto.AccommodationDetailsDto;
import mk.ukim.finki.emtlab.dto.CreateAccommodationDto;
import mk.ukim.finki.emtlab.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlab.model.views.AccommodationsPerHostView;
import mk.ukim.finki.emtlab.service.application.AccommodationApplicationService;
import mk.ukim.finki.emtlab.service.domain.AccommodationService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostService hostService;


    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll().stream().map(DisplayAccommodationDto::from).toList();
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation) {
        var host = hostService.findById(accommodation.host());

        if (host.isPresent()) {
            return accommodationService.save(accommodation.toAccommodation(host.get(), accommodation.category()))
                    .map(DisplayAccommodationDto::from);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation) {
        var host = hostService.findById(accommodation.host());

        return accommodationService.update(id, accommodation.toAccommodation(host.orElse(null), accommodation.category()))
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public void deleteById(long id) {
        accommodationService.deleteById(id);
    }

    @Override
    public List<AccommodationsPerHostView> findAllPerHost() {
        return accommodationService.findAllPerHost();
    }

    @Override
    public Optional<AccommodationDetailsDto> findDetailsById(Long id) {
        return accommodationService.findById(id).map(AccommodationDetailsDto::from);
    }
}
