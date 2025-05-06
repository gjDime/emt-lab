package mk.ukim.finki.emtlab.service.application.impl;

import mk.ukim.finki.emtlab.dto.CreateHostDto;
import mk.ukim.finki.emtlab.dto.DisplayHostDto;
import mk.ukim.finki.emtlab.model.projections.HostProjection;
import mk.ukim.finki.emtlab.model.views.HostsPerCountryView;
import mk.ukim.finki.emtlab.service.application.HostApplicationService;
import mk.ukim.finki.emtlab.service.domain.CountryService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return hostService.findAll().stream().map(DisplayHostDto::from).toList();
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto host) {
        var country = countryService.findById(host.country());

        if (country.isPresent())
            return hostService.save(host.toHost(country.get())).map(DisplayHostDto::from);
        return Optional.empty();
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto host) {
        var country = countryService.findById(host.country());
        if (country.isPresent())
            return hostService.update(id, host.toHost(country.get())).map(DisplayHostDto::from);
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }

    @Override
    public List<HostProjection> takeNameAndSurnameByProjection() {
        return hostService.takeNameAndSurnameByProjection();
    }

    @Override
    public List<HostsPerCountryView> findAllPerCountry() {
        return hostService.findAllPerCountry();
    }
}
