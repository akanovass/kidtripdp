package kz.iitu.kidtirp.model.entity;

import kz.iitu.kidtirp.model.dto.request.TariffPlanRequest;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "subscription")
@Getter
@Setter
@NoArgsConstructor
public class Subscription extends AbstractAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String type;

    private Double price;


    public void saveRequest(TariffPlanRequest request) {
        this.name = request.getName();
        this.type = request.getType();
        this.description = request.getDescription();
        this.price = request.getPrice();
    }
}
