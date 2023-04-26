package kz.iitu.kidtirp.model.dto.request;

import kz.iitu.kidtirp.model.entity.enums.DayTime;
import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TripResponse {
    private Long id;
    private TripStatus status;
    private Integer distance;
    private DayTime dayTime;
    private Boolean weekend;
    private String comment;
    private Double price;
    private String pointA;
    private String pointB;
    private Timestamp startTime;
    private Timestamp endTime;
}
