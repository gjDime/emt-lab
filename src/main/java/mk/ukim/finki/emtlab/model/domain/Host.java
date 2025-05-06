package mk.ukim.finki.emtlab.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Country country;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "host_guest",
            joinColumns = @JoinColumn(name = "host_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id"))
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
