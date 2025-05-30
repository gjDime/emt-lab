package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.model.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
