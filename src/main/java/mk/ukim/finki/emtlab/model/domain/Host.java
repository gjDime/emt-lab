package mk.ukim.finki.emtlab.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne
    private Country country;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Guest> guests;

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        guests = new ArrayList<>();
    }

    public Host() {
        guests = new ArrayList<>();
    }
}
