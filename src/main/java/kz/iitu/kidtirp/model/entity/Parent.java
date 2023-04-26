package kz.iitu.kidtirp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "parent")
@Getter
@Setter
@NoArgsConstructor
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToMany(mappedBy = "parent")
    private List<Child> children;

    private String numberCard;

    private String ownerNameCard;

    private Date cardExpireDate;

    private String cvv;

}
