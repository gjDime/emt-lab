package mk.ukim.finki.emtlab.service.application.impl;

import mk.ukim.finki.emtlab.service.application.ReservedAccommodationApplicationService;
import mk.ukim.finki.emtlab.service.domain.ReservedAccommodationService;
import org.springframework.stereotype.Service;

@Service
public class ReservedAccommodationApplicationServiceImpl implements ReservedAccommodationApplicationService {
    private final ReservedAccommodationService reservedAccommodationService;

    public ReservedAccommodationApplicationServiceImpl(ReservedAccommodationService reservedAccommodationService) {
        this.reservedAccommodationService = reservedAccommodationService;
    }
}
