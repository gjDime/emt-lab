package mk.ukim.finki.emtlab.model.domain;

import jakarta.persistence.*;
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
