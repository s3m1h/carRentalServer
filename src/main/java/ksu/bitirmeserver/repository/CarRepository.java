package ksu.bitirmeserver.repository;

import ksu.bitirmeserver.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
}
