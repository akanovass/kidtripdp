package kz.iitu.kidtirp.service;

import kz.iitu.kidtirp.model.dto.request.TripAcceptRequest;
import kz.iitu.kidtirp.model.dto.request.TripFilter;
import kz.iitu.kidtirp.model.dto.request.TripDisposableRequest;
import kz.iitu.kidtirp.model.dto.request.TripRequest;
import kz.iitu.kidtirp.model.entity.*;
import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import kz.iitu.kidtirp.repository.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class TripService {

    TripRepository tripRepository;
    DriverRepository driverRepository;
    ParentRepository parentRepository;
    ChildRepository childRepository;

    public Trip createTrip(TripRequest request) {
        Driver driver = driverRepository.findById(request.getDriverId()).orElseThrow();
        Parent parent = parentRepository.findById(request.getParentId()).orElseThrow();
        Child child = childRepository.findById(request.getChildId()).orElseThrow();
        Trip trip = new Trip(request.getStatus(),
                request.getDistance(),
                request.getDayTime(),
                request.getWeekend(),
                request.getComment(),
                request.getPrice(),
                request.getPointA(),
                request.getPointB(),
                request.getStartTime(),
                request.getEndTime(),
                driver,
                parent,
                child);
        return tripRepository.save(trip);
    }

    public Trip updateTrip(TripRequest request) {
        Trip trip = tripRepository.findById(request.getId()).orElseThrow();
        trip.setLastModifiedDate(LocalDateTime.now());
        Driver driver = driverRepository.findById(request.getDriverId()).orElseThrow();
        Parent parent = parentRepository.findById(request.getParentId()).orElseThrow();
        Child child = childRepository.findById(request.getChildId()).orElseThrow();
        trip.updateTrip(request.getStatus(),
                request.getDistance(),
                request.getDayTime(),
                request.getWeekend(),
                request.getComment(),
                request.getPrice(),
                request.getPointA(),
                request.getPointB(),
                request.getStartTime(),
                request.getEndTime(),
                driver,
                parent,
                child);
        return tripRepository.saveAndFlush(trip);
    }

    public Trip getTrip(Long id) {
        return tripRepository.findById(id).orElseThrow();
    }

    public void deleteTrip(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        tripRepository.delete(trip);
    }

    public List<Trip> getAllNewTrip() {
        return tripRepository.findAllByStatus(TripStatus.NEW);
    }

    public List<Trip> getAllTrip() {
        return tripRepository.findAll();
    }

    public Trip orderDisposableTrip(TripDisposableRequest request) {
        Trip trip = new Trip();
        int from = (int) request.getDistance();
        int price = 0;
//        List<Integer> prices = tripRepository.getPriceForLastTrip(request.getDayTime(), request.getWeekend(), from, from + 1);
        List<Integer> prices = tripRepository.getPriceForLastTrip(from, from + 1);
        for (int pr: prices) {
            price += pr;
        }
        if(prices.isEmpty()) {
            price = 1000;
        } else {
            price /= prices.size();
        }
        Parent parent = parentRepository.findById(request.getParentId()).orElseThrow();
        Child child = childRepository.findById(request.getChildId()).orElseThrow();

        trip.setNewTrip(request.getDistance(), request.getDayTime(), request.getWeekend(),
                price, request.getPointA(), request.getPointB(), parent, child);

        return tripRepository.save(trip);
    }

    public Trip changeTripStatus(TripAcceptRequest request) {
        Trip trip = tripRepository.findById(request.getTripId()).orElseThrow();
        Driver driver = driverRepository.findById(request.getDriverId()).orElseThrow();
        trip.setStatus(request.getStatus());
        trip.setDriver(driver);
        if (request.getStatus() == TripStatus.DRIVING) {
            trip.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        } else if (request.getStatus() == TripStatus.END) {
            trip.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
        }
        return tripRepository.save(trip);
    }

    public List<Trip> getAllTripParent(Long parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        return tripRepository.findAllByParent(parent);
    }

    public List<Trip> getAllTripChild(Long childId) {
        Child child = childRepository.findById(childId).orElseThrow();
        return tripRepository.findAllByChild(child);
    }

    public Page<Trip> getDrivesPageable(PageRequest pageRequest, TripFilter filter) {
        return null;
    }
}
