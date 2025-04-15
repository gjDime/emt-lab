package mk.ukim.finki.emtlab.service.application.impl;

import mk.ukim.finki.emtlab.dto.*;
import mk.ukim.finki.emtlab.model.domain.Guest;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.service.application.GuestApplicationService;
import mk.ukim.finki.emtlab.service.domain.CountryService;
import mk.ukim.finki.emtlab.service.domain.GuestService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestApplicationServiceImpl implements GuestApplicationService {

    private final GuestService guestService;
    private final HostService hostService;
    private final CountryService countryService;

    public GuestApplicationServiceImpl(GuestService guestService, HostService hostService, CountryService countryService) {
        this.guestService = guestService;
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayGuestDto> findAll() {
        return DisplayGuestDto.from(guestService.findAll());
    }

    @Override
    public Optional<DisplayGuestDto> save(CreateGuestDto guest) {
        var country = countryService.findById(guest.country());

        if (country.isPresent()) {
            return guestService.save(guest.toGuest(country.get())).map(DisplayGuestDto::from);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DisplayGuestDto> addHost(Long guestId, Long hostId) {
        var guest = guestService.findById(guestId);
        var host = hostService.findById(hostId);

        if (guest.isPresent() && host.isPresent())
            return guestService.addHost(guest.get(), host.get()).map(DisplayGuestDto::from);
        return Optional.empty();
    }

    @Override
    public Optional<DisplayGuestDto> findById(Long id) {
        return guestService.findById(id).map(DisplayGuestDto::from);
    }

    @Override
    public Optional<DisplayGuestDto> update(Long id, CreateGuestDto guest) {
        var country = countryService.findById(guest.country());

        return guestService.update(id, guest.toGuest(country.orElse(null))).map(DisplayGuestDto::from);
    }

    @Override
    public void deleteById(long id) {
        guestService.deleteById(id);
    }
}
