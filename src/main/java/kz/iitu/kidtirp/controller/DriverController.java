package kz.iitu.kidtirp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.iitu.kidtirp.model.dto.request.CarRequest;
import kz.iitu.kidtirp.model.dto.request.DriverRequest;
import kz.iitu.kidtirp.model.dto.request.TripAcceptRequest;
import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import kz.iitu.kidtirp.service.ApplicationService;
import kz.iitu.kidtirp.service.TripService;
import kz.iitu.kidtirp.service.DriverService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/driver")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DriverController {

    DriverService driverService;
    TripService tripService;

    @PutMapping("/public/register")
    public ResponseEntity<?> register(@Valid @RequestBody DriverRequest request) {
        return ResponseEntity.ok(driverService.createDriver(request));
    }

    @PostMapping("/update")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateProfile(@RequestBody DriverRequest request) {
        return ResponseEntity.ok(driverService.updateDriver(request));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }

    @PostMapping("/car/add/{driverId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> addCar(@RequestBody CarRequest request, @PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.addCar(request, driverId));
    }

    @PostMapping("/car/update/{driverId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateCar(@RequestBody CarRequest request, @PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.updateCar(request, driverId));
    }

    @PostMapping("/area/select/{driverId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> selectArea(@PathVariable Long driverId, @RequestParam String area) {
        return ResponseEntity.ok(driverService.selectArea(driverId, area));
    }

    @GetMapping("/shedule/{driverId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getSheduleDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.getAllDrives(driverId));
    }

    @GetMapping("/shedule/new/{driverId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getSheduleDriverStatusNew(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.getAllNewDrives(driverId));
    }

    @PostMapping("/trip/status/change")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> acceptTrip(@RequestBody TripAcceptRequest request) {
        return ResponseEntity.ok(tripService.changeTripStatus(request));
    }


    @GetMapping("/status/new")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getAllNewTrip() {
        return ResponseEntity.ok(tripService.getAllNewTrip());
    }

//    @PatchMapping("/drive/apply/{driveId}")
//    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
//    public ResponseEntity<?> applyDrive(@PathVariable Long driveId, @RequestParam boolean accept) {
//        return ResponseEntity.ok(tripService.(driveId, accept));
//    }
//
//    @PostMapping("/drive/start/{driveId}")
//    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
//    public ResponseEntity<?> startDrive(@PathVariable Long driveId, @RequestParam String status) {
//        return ResponseEntity.ok(tripService.startOrWaitTrip(driveId, TripStatus.valueOf(status)));
//    }
//
//    @PostMapping("/drive/end/{driveId}")
//    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
//    public ResponseEntity<?> startDrive(@PathVariable Long driveId) {
//        return ResponseEntity.ok(tripService.endTrip(driveId));
//    }
//
//    @PostMapping("/drive/change/status/{driveId}")
//    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
//    public ResponseEntity<?> changeStatus(@PathVariable Long driveId, @RequestParam String status) {
//        return ResponseEntity.ok(tripService.changeStatus(driveId, TripStatus.valueOf(status)));
//    }

    @GetMapping("/drive/warning")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> driveWarning() {
        return ResponseEntity.ok("вы сбились с пути");
    }

    @GetMapping("/salary/{driverId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getSalary(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.getDriver(driverId));
    }

}
