package kz.iitu.kidtirp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.iitu.kidtirp.model.dto.request.ChildRequest;
import kz.iitu.kidtirp.model.dto.request.LocationDto;
import kz.iitu.kidtirp.service.ParentService;
import kz.iitu.kidtirp.service.TripService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/child")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ChildController {

    TripService tripService;
    ParentService parentService;

    @GetMapping("/schedule/{childId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> schedule(@PathVariable Long childId) {
        return ResponseEntity.ok(tripService.getAllTripChild(childId));
    }

    @PostMapping("/location/{childId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateLocation(@RequestBody LocationDto location, @PathVariable Long childId) {
        return ResponseEntity.ok(parentService.updateLocation(location, childId));
    }

    @GetMapping("/location/{childId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getChildLocationLatitude(@PathVariable Long childId) {
        return ResponseEntity.ok(parentService.getChildLocationLatitude(childId));
    }

    @GetMapping("/user/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getChildByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(parentService.getChildByUser(id));
    }

    @GetMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getChildById(@PathVariable Long id) {
        return ResponseEntity.ok(parentService.getChildById(id));
    }

    @PutMapping("/update")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateChild(@RequestBody ChildRequest request) {
        return ResponseEntity.ok(parentService.updateChild(request));
    }

    @DeleteMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public void deleteChild(@PathVariable Long id) {
        parentService.deleteChild(id);
    }

    @DeleteMapping("/delete-parent/{childId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> deleteParentFromChild(@PathVariable Long childId) {
        return ResponseEntity.ok(parentService.deleteChildFromParent(childId));
    }
}
