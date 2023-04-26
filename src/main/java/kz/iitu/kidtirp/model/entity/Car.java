package kz.iitu.kidtirp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "car")
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String markAndModel;

    private String color;

    private Integer yearManufacture;

    @NotNull
    private String stateNumber;

    public Car(String markAndModel, String color, Integer yearManufacture, String stateNumber) {
        this.markAndModel = markAndModel;
        this.color = color;
        this.yearManufacture = yearManufacture;
        this.stateNumber = stateNumber;
    }
}
