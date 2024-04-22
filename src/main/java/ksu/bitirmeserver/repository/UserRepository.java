package ksu.bitirmeserver.repository;

import ksu.bitirmeserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
