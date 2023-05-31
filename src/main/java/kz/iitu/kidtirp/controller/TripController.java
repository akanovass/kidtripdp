package kz.iitu.kidtirp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.iitu.kidtirp.model.dto.request.TripAcceptRequest;
import kz.iitu.kidtirp.model.dto.request.TripDisposableRequest;
import kz.iitu.kidtirp.model.dto.request.TripRequest;
import kz.iitu.kidtirp.service.TripService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/trip")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TripController {

    TripService tripService;

    @PostMapping("/order/disposable")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> ordeDisposableTrip(@RequestBody TripDisposableRequest tripDisposableRequest) {
        return ResponseEntity.ok(tripService.orderDisposableTrip(tripDisposableRequest));
    }

    @PostMapping("/status/change")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> acceptTrip(@RequestBody TripAcceptRequest request) {
        return ResponseEntity.ok(tripService.changeTripStatus(request));
    }

    @GetMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getTripById(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.getTrip(id));
    }

    @DeleteMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }

    @GetMapping("/status/new")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getAllNewTrip() {
        return ResponseEntity.ok(tripService.getAllNewTrip());
    }


    @PostMapping("/create")
    public ResponseEntity<?> createTrip(@RequestBody TripRequest tripRequest) {
        return ResponseEntity.ok(tripService.createTrip(tripRequest));
    }

}
