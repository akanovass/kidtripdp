package kz.iitu.kidtirp.model.dto.request;

import kz.iitu.kidtirp.model.entity.enums.DayTime;
import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TripRequest {

    private Long id;
    private TripStatus status;
    private Double distance;
    private DayTime dayTime;
    private Boolean weekend;
    private String comment;
    private Integer price;
    private String pointA;
    private String pointB;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long driverId;
    private Long parentId;
    private Long childId;
}
