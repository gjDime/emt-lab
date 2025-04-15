package mk.ukim.finki.emtlab.service.application;

import mk.ukim.finki.emtlab.dto.CreateGuestDto;
import mk.ukim.finki.emtlab.dto.CreateHostDto;
import mk.ukim.finki.emtlab.dto.DisplayGuestDto;
import mk.ukim.finki.emtlab.model.domain.Guest;
import mk.ukim.finki.emtlab.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface GuestApplicationService {
    List<DisplayGuestDto> findAll();

    Optional<DisplayGuestDto> save(CreateGuestDto guest);

    Optional<DisplayGuestDto> addHost(Long guest, Long host);

    Optional<DisplayGuestDto> findById(Long id);

    Optional<DisplayGuestDto> update(Long id, CreateGuestDto guest);

    void deleteById(long id);
}
