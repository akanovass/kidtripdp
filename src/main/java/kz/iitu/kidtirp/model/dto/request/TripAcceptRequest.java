package kz.iitu.kidtirp.model.dto.request;

import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import lombok.Data;

@Data
public class TripAcceptRequest {

    private Long tripId;
    private Long driverId;
    private TripStatus status;
}
