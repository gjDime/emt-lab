package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.model.enumerations.Category;

import java.util.List;


public record CreateAccommodationDto(
        String name,
        Integer numRooms,
        Long host,
        Category category
) {
    public Accommodation toAccommodation(Host host, Category category) {
        return new Accommodation(name, numRooms, host, category);
    }

    public static CreateAccommodationDto from(Accommodation accommodation) {
        return new CreateAccommodationDto(accommodation.getName(),
                accommodation.getNumberOfRooms(),
                accommodation.getHost().getId(),
                accommodation.getCategory());
    }

    public static List<CreateAccommodationDto> from (List<Accommodation> accommodationList){
        return accommodationList.stream().map(CreateAccommodationDto::from).toList();
    }
}
