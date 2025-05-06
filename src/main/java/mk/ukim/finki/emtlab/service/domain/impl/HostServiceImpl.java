package mk.ukim.finki.emtlab.service.domain.impl;

import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.model.events.HostCreatedEvent;
import mk.ukim.finki.emtlab.model.projections.HostProjection;
import mk.ukim.finki.emtlab.model.views.HostsPerCountryView;
import mk.ukim.finki.emtlab.repository.HostRepository;
import mk.ukim.finki.emtlab.repository.HostsPerCountryRepository;
import mk.ukim.finki.emtlab.service.domain.CountryService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final HostsPerCountryRepository hostsPerCountryRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService, HostsPerCountryRepository hostsPerCountryRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.hostsPerCountryRepository = hostsPerCountryRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> save(Host host) {
        Optional<Host> savedHost = Optional.empty();
        if (host.getCountry() != null && countryService.findById(host.getCountry().getId()).isPresent()) {
            savedHost = Optional.of(hostRepository.save(
                    new Host(host.getName(), host.getSurname(), countryService.findById(host.getCountry().getId()).get())));
            applicationEventPublisher.publishEvent(new HostCreatedEvent(host));
        }

        return savedHost;
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id).map(existingHost -> {
            if (host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if (host.getSurname() != null) {
                existingHost.setName(host.getSurname());
            }
            if (host.getCountry() != null && countryService.findById(host.getCountry().getId()).isPresent()) {
                existingHost.setCountry(countryService.findById(host.getCountry().getId()).get());
            }
            var updatedHost = hostRepository.save(existingHost);
            applicationEventPublisher.publishEvent(new HostCreatedEvent(host));
            return updatedHost;
        });
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
        applicationEventPublisher.publishEvent(new HostCreatedEvent(new Host()));
    }

    @Override
    public List<HostProjection> takeNameAndSurnameByProjection() {
        return hostRepository.takeNameAndSurnameByProjection();
    }

    @Override
    public void refreshMaterializedView() {
        hostsPerCountryRepository.refreshMaterializedView();
    }

    @Override
    public List<HostsPerCountryView> findAllPerCountry() {
        return hostsPerCountryRepository.findAll();
    }
}
