package mk.ukim.finki.emtlab.service.domain.impl;

import mk.ukim.finki.emtlab.model.domain.Guest;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.repository.GuestRepository;
import mk.ukim.finki.emtlab.service.domain.CountryService;
import mk.ukim.finki.emtlab.service.domain.GuestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final CountryService countryService;

    public GuestServiceImpl(GuestRepository guestRepository, CountryService countryService) {
        this.guestRepository = guestRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public Optional<Guest> addHost(Guest guest, Host host) {
        guest.getHosts().add(host);
        host.getGuests().add(guest);
        return Optional.of(guestRepository.save(guest));
    }

    @Override
    public Optional<Guest> save(Guest guest) {

//        Guest guest1 = guestRepository.save(new Guest("gostin1", country1));
//        Guest guest2 = guestRepository.save(new Guest("gostin2", country2));
//
//        guest1.getHosts().add(host1);
//        host1.getGuests().add(guest1);
//        guestRepository.save(guest1);


        if (guest.getCountry() != null && countryService.findById(guest.getCountry().getId()).isPresent())
            return Optional.of(guestRepository.save
                    (new Guest(guest.getName(), countryService.findById(guest.getCountry().getId()).get())));
        return Optional.empty();
    }

    @Override
    public Optional<Guest> findById(Long id) {
        return guestRepository.findById(id);
    }

    @Override
    public Optional<Guest> update(Long id, Guest guest) {
        return guestRepository.findById(id).map(g -> {
            if (guest.getName() != null)
                g.setName(guest.getName());
            if (guest.getCountry() != null &&
                    countryService.findById(guest.getCountry().getId()).isPresent())
                g.setCountry(countryService.findById(guest.getCountry().getId()).get());

            return guestRepository.save(g);
        });
    }

    @Override
    public void deleteById(long id) {
        guestRepository.deleteById(id);
    }
}
