package kz.iitu.kidtirp.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationDto {
    Long id;
    String name;
    String status;
    String latitude;
    String longitude;
    Date time;
    String coordinate;
}
