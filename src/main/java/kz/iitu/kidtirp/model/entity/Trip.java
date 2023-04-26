package kz.iitu.kidtirp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.iitu.kidtirp.model.entity.enums.DayTime;
import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "trip")
@NoArgsConstructor
@AllArgsConstructor
public class Trip extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private TripStatus status;

    @Column
    private Double distance;

    @Column
    @Enumerated
    private DayTime dayTime;

    @Column(name = "weekend")
    private Boolean weekend;

    @Column
    private String comment;

    @Column
    private Integer price;

    @Column
    private String pointA;

    @Column
    private String pointB;

    @Column
    private Timestamp startTime;

    @Column
    private Timestamp endTime;

    @ManyToOne
    @JoinColumn(name="driver_id")
    private Driver driver;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="parent_id")
    private Parent parent;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="child_id")
    private Child child;

    @Column(name = "resheduled")
    private boolean rescheduled = false;

    @Column(name = "one_time_drive")
    private boolean oneTimeDrive = false;

    public Trip(TripStatus status, Double distance, DayTime dayTime, Boolean weekend, String comment, Integer price, String pointA, String pointB, Timestamp startTime, Timestamp endTime, Driver driver, Parent parent, Child child) {
        this.status = status;
        this.distance = distance;
        this.dayTime = dayTime;
        this.weekend = weekend;
        this.comment = comment;
        this.price = price;
        this.pointA = pointA;
        this.pointB = pointB;
        this.startTime = startTime;
        this.endTime = endTime;
        this.driver = driver;
        this.parent = parent;
        this.child = child;
    }

    public void setNewTrip(Double distance, DayTime dayTime, Boolean weekend, int price, String pointA, String pointB, Parent parent, Child child) {
        this.status = TripStatus.NEW;
        this.distance = distance;
        this.dayTime = dayTime;
        this.weekend = weekend;
        this.price = price;
        this.pointA = pointA;
        this.pointB = pointB;
        this.parent = parent;
        this.child = child;
        this.oneTimeDrive = true;
    }

    public void updateTrip(TripStatus status, Double distance, DayTime dayTime, Boolean weekend, String comment, Integer price, String pointA, String pointB, Timestamp startTime, Timestamp endTime, Driver driver, Parent parent, Child child) {
        this.status = status;
        this.distance = distance;
        this.dayTime = dayTime;
        this.weekend = weekend;
        this.comment = comment;
        this.price = price;
        this.pointA = pointA;
        this.pointB = pointB;
        this.startTime = startTime;
        this.endTime = endTime;
        this.driver = driver;
        this.parent = parent;
        this.child = child;
    }
}
