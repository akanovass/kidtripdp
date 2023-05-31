package kz.iitu.kidtirp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "child_location")
@Getter
@Setter
@NoArgsConstructor
public class ChildLocation extends AbstractAuditingEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private String status;
    private String latitude;
    private String longitude;
    private Date time;
    private String coordinate;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="child_id", nullable=false)
    private Child child;
}
