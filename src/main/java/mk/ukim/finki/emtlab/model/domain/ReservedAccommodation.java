package mk.ukim.finki.emtlab.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ReservedAccommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accommodationId;

    private String userId;

    public ReservedAccommodation(Long accommodationId, String userId) {
        this.accommodationId = accommodationId;
        this.userId = userId;
    }

    public ReservedAccommodation() {

    }
}
