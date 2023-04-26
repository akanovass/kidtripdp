package kz.iitu.kidtirp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.iitu.kidtirp.model.entity.enums.Gender;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "child")
@Getter
@Setter
@NoArgsConstructor
public class Child {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private Date birthDate;

    private Gender gender;

    private String address;

    private String schoolName;

    private Integer classN;

    private String medicalConditions;

    private String photoUrl;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name="parent_id")
    private Parent parent;

    public Child(User user, Date birthDate, Gender gender, String address, String schoolName, Integer classN, String medicalConditions, String photoUrl, Parent parent) {
        this.user = user;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.schoolName = schoolName;
        this.classN = classN;
        this.medicalConditions = medicalConditions;
        this.photoUrl = photoUrl;
        this.parent = parent;
    }
}
