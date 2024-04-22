package ksu.bitirmeserver.repository;

import ksu.bitirmeserver.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    boolean existsById(Long id);
}
