package kz.iitu.kidtirp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.iitu.kidtirp.model.dto.request.*;
import kz.iitu.kidtirp.model.dto.response.MessageResponse;
import kz.iitu.kidtirp.service.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdminController {

    UserService userService;
    ParentService parentService;
    DriverService driverService;
    TripService tripService;
    ApplicationService applicationService;
    SubscriptionService SubscriptionService;

    @PutMapping("/driver/create")
    @Operation(summary = "createUser", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createParent(@RequestBody DriverRequest request) {
        return ResponseEntity.ok(driverService.createDriver(request));
    }

    @PutMapping("/child/create/{parentId}")
    @Operation(summary = "createUser", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createDriver(@RequestBody ChildRequest request, @PathVariable Long parentId) {
        return ResponseEntity.ok(parentService.addChild(request, parentId));
    }

    @PutMapping("/parent/create")
    @Operation(summary = "createUser", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createChild(@RequestBody ParentRequest request) {
        return ResponseEntity.ok(parentService.registerParent(request));
    }

    @GetMapping("/parent/all")
    @Operation(summary = "getAllParent", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getAllParent() {
        return ResponseEntity.ok(parentService.getAllParents());
    }

    @GetMapping("/driver/all")
    @Operation(summary = "getAllDriver", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getAllDriver() {
        return ResponseEntity.ok(driverService.getAllDriver());
    }

    @GetMapping("/child/all")
    @Operation(summary = "getAllChild", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getAllChild() {
        return ResponseEntity.ok(parentService.getAllChild());
    }

    @DeleteMapping("/user/delete/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse(true, "User successfully deleted!"));
    }

    @DeleteMapping("/trip/delete/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.ok(new MessageResponse(true, "Drive successfully deleted!"));
    }

    @PostMapping("/app/apply/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> applyApplication(@PathVariable Long id, @RequestParam Boolean apply) {
        return ResponseEntity.ok(applicationService.applyApplication(id, apply));
    }

    @PostMapping("/trip/all")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> driveTable() {
        return ResponseEntity.ok(tripService.getAllTrip());
    }

    @PostMapping("/trip/filter")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> driveTableFilter(@RequestBody TripFilter filter, @RequestParam int page, @RequestParam int size) {
        PageRequest request = PageRequest.of(page, size);
        return ResponseEntity.ok(tripService.getDrivesPageable(request, filter));
    }

    @PostMapping("/driver/salary")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> paySalaryDriver(@RequestParam Long driverId, @RequestParam Double amount) {
        return ResponseEntity.ok("");
    }

    @PutMapping("/tariff/create")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createTariff(@RequestBody TariffPlanRequest request) {
        return ResponseEntity.ok(SubscriptionService.createTariffPlan(request));
    }

    @PostMapping("/tariff/update")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateTariffPlan(@RequestBody TariffPlanRequest request) {
        return ResponseEntity.ok(SubscriptionService.updateTariffPlan(request));
    }
}
