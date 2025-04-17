package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.model.domain.ReservedAccommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedAccommodationRepository extends JpaRepository<ReservedAccommodation,Long> {
}
