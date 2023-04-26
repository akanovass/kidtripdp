package kz.iitu.kidtirp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "parent_subscriptions")
@Getter
@Setter
@NoArgsConstructor
public class ParentSubscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double price;

    private String status;

    private Date startDate;

    private Date expiryDate;

    private Integer availableTrips;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="parent_id", nullable=false)
    private Parent parent;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="child_id", nullable=false)
    private Child child;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="schedule_id", nullable=false)
    private Schedule schedule;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="driver_id", nullable=false)
    private Driver driver;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="subscription_id", nullable=false)
    private Subscription subscription;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="trip_id", nullable=false)
    private Trip trip;
}
