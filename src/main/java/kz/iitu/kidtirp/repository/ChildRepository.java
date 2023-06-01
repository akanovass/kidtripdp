package kz.iitu.kidtirp.repository;

import kz.iitu.kidtirp.model.entity.Child;
import kz.iitu.kidtirp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    Child findByUser(User user);
}
