package kz.iitu.kidtirp.service;

import kz.iitu.kidtirp.model.dto.request.CarRequest;
import kz.iitu.kidtirp.model.dto.request.DriverRequest;
import kz.iitu.kidtirp.model.dto.request.SignupRequest;
import kz.iitu.kidtirp.model.entity.Car;
import kz.iitu.kidtirp.model.entity.Trip;
import kz.iitu.kidtirp.model.entity.Driver;
import kz.iitu.kidtirp.model.entity.User;
import kz.iitu.kidtirp.model.entity.enums.ERole;
import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import kz.iitu.kidtirp.repository.CarRepository;
import kz.iitu.kidtirp.repository.TripRepository;
import kz.iitu.kidtirp.repository.DriverRepository;
import kz.iitu.kidtirp.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class DriverService {

    DriverRepository driverRepository;
    UserService userService;
    TripRepository tripRepository;
    CarRepository carRepository;

    public Driver createDriver(DriverRequest request) {
        User user = userService.createUser(new SignupRequest(request.getUsername(), request.getFullName(), request.getGmail(), request.getPhoneNumber(), request.getPassword(), ERole.DRIVER));
        Driver driver = new Driver(user, request.getIin(), request.getBirthDate(), request.getArea(), request.getExperience(), request.getCarLicencePlate(),
                request.getDateOfIssue(), request.getExpiryDate(), request.getWorkDistinct(), request.getIdentificationPhoto(), request.getDriverLicensePhoto(), request.getTechPassportPhoto());
        return driverRepository.save(driver);
    }

    public Driver updateDriver(DriverRequest request) {
        Driver driver = driverRepository.findById(request.getId()).orElseThrow();
        driver.setIin(request.getIin());
        driver.setArea(request.getArea());
        driver.setBirthDate(request.getBirthDate());
        driver.setExperience(request.getExperience());
        driver.setCarLicencePlate(request.getCarLicencePlate());
        driver.setDateOfIssue(request.getDateOfIssue());
        driver.setExpiryDate(request.getExpiryDate());
        driver.setWorkDistinct(request.getWorkDistinct());
        driver.setIdentificationPhoto(request.getIdentificationPhoto());
        driver.setDriverLicensePhoto(request.getDriverLicensePhoto());
        driver.setTechPassportPhoto(request.getTechPassportPhoto());
        return driverRepository.save(driver);
    }

    public Driver getDriverByUser(Long userId) {
        User user = userService.getUserById(userId);
        return driverRepository.findByUser(user);
    }

    public Driver getById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    public void deleteDriver(Long driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow();
        driverRepository.delete(driver);
    }

    public Driver addCar(CarRequest request, Long driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow();
        Car car = new Car(request.getMarkAndModel(), request.getColor(), request.getYearManufacture(), request.getStateNumber());
        car = carRepository.save(car);
        driver.setCarId(car.getId());
        return driverRepository.saveAndFlush(driver);
    }

    public Driver updateCar(CarRequest request, Long driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow();
        Car car = carRepository.findById(request.getId()).orElseThrow();
        car.setColor(request.getColor());
        car.setMarkAndModel(request.getMarkAndModel());
        car.setYearManufacture(request.getYearManufacture());
        car.setStateNumber(request.getStateNumber());
        car = carRepository.saveAndFlush(car);
        driver.setCarId(car.getId());
        return driverRepository.saveAndFlush(driver);
    }

    public Driver selectArea(Long driverId, String area) {
        Driver driver = driverRepository.findById(driverId).orElseThrow();
        driver.setArea(area);
        return driverRepository.save(driver);
    }

    public Driver getDriver(Long id) {
        return driverRepository.findById(id).orElseThrow();
    }

    public List<Driver> getAllDriver() {
        return driverRepository.findAll();
    }

    public List<Trip> getAllDrives(Long driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow();
        return tripRepository.findAllByDriver(driver);
    }

    public List<Trip> getAllNewDrives(Long driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow();
        return tripRepository.findAllByDriverAndStatus(driver, TripStatus.NEW);
    }

}
