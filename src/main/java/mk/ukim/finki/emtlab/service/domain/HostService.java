package mk.ukim.finki.emtlab.service.domain;

import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.model.projections.HostProjection;
import mk.ukim.finki.emtlab.model.views.AccommodationsPerHostView;
import mk.ukim.finki.emtlab.model.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> save (Host host);

    Optional<Host> findById(Long id);

    Optional<Host> update (Long id, Host host);

    void deleteById(Long id);

    List<HostProjection> takeNameAndSurnameByProjection();

    void refreshMaterializedView();

    List<HostsPerCountryView> findAllPerCountry();
}
