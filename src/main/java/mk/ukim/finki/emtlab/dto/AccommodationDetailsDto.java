package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.enumerations.Category;

public record AccommodationDetailsDto(
        Long id,
        String name,
        Integer numRooms,
        Category category,
        String hostName,
        String hostSurname,
        String hostCountryName,
        String HostCountryContinent
) {
    public static AccommodationDetailsDto from(Accommodation accommodation) {
        return new AccommodationDetailsDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getNumberOfRooms(),
                accommodation.getCategory(),
                accommodation.getHost().getName(),
                accommodation.getHost().getSurname(),
                accommodation.getHost().getCountry().getName(),
                accommodation.getHost().getCountry().getContinent());
    }
}
