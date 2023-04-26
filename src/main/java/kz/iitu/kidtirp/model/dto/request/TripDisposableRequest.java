package kz.iitu.kidtirp.model.dto.request;

import kz.iitu.kidtirp.model.entity.enums.DayTime;
import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TripDisposableRequest {

    private String pointA;
    private String pointB;
    private double distance;
    private DayTime dayTime;
    private Boolean weekend;
    private Timestamp startTime;
    private Timestamp endTime;
    private TripStatus status;
    private Long parentId;
    private Long childId;
}
