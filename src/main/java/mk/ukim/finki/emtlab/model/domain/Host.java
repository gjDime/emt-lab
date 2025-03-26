package mk.ukim.finki.emtlab.model.domain;

import jakarta.persistence.*;
import lombok.Data;

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

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Host() {

    }
}
