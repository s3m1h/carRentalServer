package ksu.bitirmeserver.repository;

import ksu.bitirmeserver.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
