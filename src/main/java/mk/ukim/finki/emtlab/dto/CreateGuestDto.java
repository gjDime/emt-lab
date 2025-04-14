package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.Country;
import mk.ukim.finki.emtlab.model.domain.Guest;

import java.util.List;
import java.util.stream.Collectors;

public record CreateGuestDto(
        String name,
        Long country

) {
    public Guest toGuest(Country country) {
        return new Guest(name, country);
    }

    public static CreateGuestDto from(Guest guest) {
        return new CreateGuestDto(guest.getName(), guest.getCountry().getId());
    }

    public static List<CreateGuestDto> from(List<Guest> guests) {
        return guests.stream().map(CreateGuestDto::from).toList();
    }
}
