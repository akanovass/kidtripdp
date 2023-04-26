package kz.iitu.kidtirp.repository;

import kz.iitu.kidtirp.model.entity.ParentSubscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentSubscriptionsRepository extends JpaRepository<ParentSubscriptions, Long> {
}
