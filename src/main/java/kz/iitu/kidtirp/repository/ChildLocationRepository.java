package kz.iitu.kidtirp.repository;

import kz.iitu.kidtirp.model.entity.Child;
import kz.iitu.kidtirp.model.entity.ChildLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildLocationRepository extends JpaRepository<ChildLocation, Long> {
    ChildLocation findByChild(Child child);
}
