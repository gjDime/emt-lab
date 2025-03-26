package mk.ukim.finki.emtlab.service.application;

import mk.ukim.finki.emtlab.dto.CreateHostDto;
import mk.ukim.finki.emtlab.dto.DisplayHostDto;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> save (CreateHostDto host);

    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> update (Long id, CreateHostDto host);

    void deleteById(Long id);
}
