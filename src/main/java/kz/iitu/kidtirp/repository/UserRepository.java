package kz.iitu.kidtirp.repository;

import kz.iitu.kidtirp.model.entity.User;
import kz.iitu.kidtirp.model.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    List<User> findAllByRole(ERole role);

}
