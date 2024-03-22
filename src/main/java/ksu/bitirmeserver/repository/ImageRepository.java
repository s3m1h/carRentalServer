package ksu.bitirmeserver.repository;

import ksu.bitirmeserver.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
