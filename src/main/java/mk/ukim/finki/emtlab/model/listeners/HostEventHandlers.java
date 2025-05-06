package mk.ukim.finki.emtlab.model.listeners;

import mk.ukim.finki.emtlab.model.events.HostCreatedEvent;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {
    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostCreated(HostCreatedEvent event) {
        hostService.refreshMaterializedView();
    }
}
