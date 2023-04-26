package kz.iitu.kidtirp.repository;

import kz.iitu.kidtirp.model.entity.*;
import kz.iitu.kidtirp.model.entity.enums.DayTime;
import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByDriver(Driver driver);
    List<Trip> findAllByDriverAndStatus(Driver driver, TripStatus status);

    @Query("select t from Trip t where t.status = :status")
    List<Trip> findAllByStatus(@Param("status") TripStatus status);
    List<Trip> findAllByParent(Parent parent);
    List<Trip> findAllByChild(Child child);

//    @Query(value = "select price from trip t where t.distance <= :to and t.distance >= :from and day_time = :day and weekend = :weekend and status = 'END' order by created_date desc limit 10", nativeQuery = true)
    @Query(value = "select price from trip t where t.distance <= :to and t.distance >= :from and status = 'END' order by created_date desc limit 10", nativeQuery = true)
    List<Integer> getPriceForLastTrip(@Param("from") int from, @Param("to") int to);
}
