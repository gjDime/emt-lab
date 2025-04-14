package mk.ukim.finki.emtlab.model.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToOne
    private Country country;

    @ManyToMany(mappedBy = "guests", cascade = CascadeType.MERGE)
    private List<Host> hosts;

    public Guest() {
        hosts = new ArrayList<>();
    }

    public Guest(String name, Country country) {
        this.name = name;
        this.country = country;
        hosts = new ArrayList<>();
    }
}
