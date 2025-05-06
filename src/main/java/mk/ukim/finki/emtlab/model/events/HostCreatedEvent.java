package mk.ukim.finki.emtlab.model.events;

import lombok.Getter;
import mk.ukim.finki.emtlab.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostCreatedEvent extends ApplicationEvent {
    private LocalDateTime when;

    public HostCreatedEvent(Host source) {
        super(source);
    }

    public HostCreatedEvent(Host source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
