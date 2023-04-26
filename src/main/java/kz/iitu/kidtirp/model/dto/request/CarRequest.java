package kz.iitu.kidtirp.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

    private Long id;
    private String markAndModel;
    private String color;
    private Integer yearManufacture;
    private String stateNumber;
}
