package mk.ukim.finki.emtlab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emtlab.model.enumerations.Category;

@Entity
@Data
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer numberOfRooms;

    @ManyToOne
    private Host host;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Accommodation(String name, Integer numberOfRooms, Host host, Category category) {
        this.name = name;
        this.numberOfRooms = numberOfRooms;
        this.host = host;
        this.category = category;
    }

    public Accommodation() {

    }
}
