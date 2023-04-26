package kz.iitu.kidtirp.model.entity;


import kz.iitu.kidtirp.model.entity.enums.ApplicationStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "application")
@Getter
@Setter
@NoArgsConstructor
public class Application extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ApplicationStatus status;

    private String pointA;

    private String pointB;

    @NotNull
    private Double price;

    private Integer distance;

    private Integer estimatedTime;

    private Long childId;

    private Long parentId;
}
