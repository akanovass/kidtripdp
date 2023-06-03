package kz.iitu.kidtirp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.iitu.kidtirp.model.dto.request.ChildRequest;
import kz.iitu.kidtirp.model.dto.request.ParentRequest;
import kz.iitu.kidtirp.model.dto.request.TripDisposableRequest;
import kz.iitu.kidtirp.service.TripService;
import kz.iitu.kidtirp.service.ParentService;
import kz.iitu.kidtirp.service.SubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/parent")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ParentController {

    ParentService parentService;
    SubscriptionService subscriptionService;
    TripService tripService;

    @PostMapping("/public/register")
    public ResponseEntity<?> registerParent(@RequestBody ParentRequest request) {
        return ResponseEntity.ok(parentService.registerParent(request));
    }

    @GetMapping("/user/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getParentById(@PathVariable Long id) {
        return ResponseEntity.ok(parentService.getParentByUserId(id));
    }

    @GetMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(parentService.getParentById(id));
    }

    @PostMapping("/update")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateParent(@RequestBody ParentRequest request) {
        return ResponseEntity.ok(parentService.updateParent(request));
    }

    @PutMapping("/add/child/{parentId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> addChildren(@PathVariable Long parentId,
                                         @RequestBody ChildRequest request) {
        return ResponseEntity.ok(parentService.addChild(request, parentId));
    }

//    @PutMapping("/tariff/{parentId}")
//    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
//    public ResponseEntity<?> selectTariff(@PathVariable Long parentId,
//                                          @RequestParam Long tariffId) {
//        return ResponseEntity.ok(SubscriptionService.selectTariffPlan(parentId, tariffId));
//    }

    @GetMapping("/drive/list/{parentId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getDriveList(@PathVariable Long parentId) {
        return ResponseEntity.ok(tripService.getAllTripParent(parentId));
    }

    @PostMapping("drive/onetime/order")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createOneTimeDrive(@RequestBody TripDisposableRequest request) {
        return ResponseEntity.ok(tripService.orderDisposableTrip(request));
    }

    @PostMapping("/select/driver/{parentId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> selectDriver(@PathVariable Long parentId,
                                          @RequestParam Long driverId) {
        return ResponseEntity.ok(parentService.selectDriver(driverId, parentId));
    }

    @GetMapping("/children/{parentId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getAllByParent(@PathVariable Long parentId) {
        return ResponseEntity.ok(parentService.getAllChildByParent(parentId));
    }

    @GetMapping("/children/location/{parentId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getAllChildLocations(@PathVariable Long parentId) {
        return ResponseEntity.ok(parentService.getChildLocations(parentId));
    }

}
