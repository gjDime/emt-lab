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

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Host host;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Boolean rented;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Accommodation(String name, Integer numberOfRooms, Host host, Category category) {
        this.name = name;
        this.numberOfRooms = numberOfRooms;
        this.host = host;
        this.category = category;
        rented = false;
    }

    public Accommodation() {
        rented = false;
    }
}
