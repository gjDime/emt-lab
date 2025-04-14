package mk.ukim.finki.emtlab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.Country;
import mk.ukim.finki.emtlab.model.domain.Guest;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.model.enumerations.Category;
import mk.ukim.finki.emtlab.repository.AccommodationRepository;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import mk.ukim.finki.emtlab.repository.GuestRepository;
import mk.ukim.finki.emtlab.repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;

    public DataInitializer(AccommodationRepository accommodationRepository, CountryRepository countryRepository, HostRepository hostRepository, GuestRepository guestRepository) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
    }

    @PostConstruct
    public void init() {
        Country country1 = countryRepository.save(new Country("Macedonia", "Europe"));
        Country country2 = countryRepository.save(new Country("Japan", "Asia"));
        Country country3 = countryRepository.save(new Country("Argentina", "South America"));

        Host host1 = hostRepository.save(new Host("Boris", "Manev", country1));
        Host host2 = hostRepository.save(new Host("John", "Doe", country2));
        Host host3 = hostRepository.save(new Host("Jane", "Doe", country3));

        Accommodation accommodation1 = accommodationRepository.save(new Accommodation("Ohrid Apartmani", 2, host1, Category.APARTMENT));
        Accommodation accommodation2 = accommodationRepository.save(new Accommodation("Tokyo Room Special", 4, host2, Category.ROOM));
        Accommodation accommodation3 = accommodationRepository.save(new Accommodation("Messi childhood House", 3, host3, Category.HOUSE));

        Guest guest1 = guestRepository.save(new Guest("gostin1", country1));
        Guest guest2 = guestRepository.save(new Guest("gostin2", country2));

        guest1.getHosts().add(host1);
        host1.getGuests().add(guest1);
        guestRepository.save(guest1);
    }
}

