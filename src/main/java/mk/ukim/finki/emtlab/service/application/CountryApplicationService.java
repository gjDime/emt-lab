package mk.ukim.finki.emtlab.service.application;

import mk.ukim.finki.emtlab.dto.CreateCountryDto;
import mk.ukim.finki.emtlab.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> save(CreateCountryDto country);

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto country);

    void deleteById(long id);
}
