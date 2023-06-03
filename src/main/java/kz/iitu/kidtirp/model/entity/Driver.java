package kz.iitu.kidtirp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "driver")
@Getter
@Setter
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private String iin;

    private Date birthDate;

    private String area;

    private Integer experience;

    private String carLicencePlate;

    private Date dateOfIssue;

    private Date expiryDate;

    private String workDistinct;

    private String identificationPhoto;

    private String driverLicensePhoto;

    private String techPassportPhoto;

    private Long carId;

    private String sex;

    private Double rating;

    public Driver(User user, String iin, Date birthDate, String area, Integer experience, String carLicencePlate, Date dateOfIssue, Date expiryDate, String workDistinct, String identificationPhoto, String driverLicensePhoto, String techPassportPhoto,  String sex, Double rating) {
        this.user = user;
        this.iin = iin;
        this.birthDate = birthDate;
        this.area = area;
        this.experience = experience;
        this.carLicencePlate = carLicencePlate;
        this.dateOfIssue = dateOfIssue;
        this.expiryDate = expiryDate;
        this.workDistinct = workDistinct;
        this.identificationPhoto = identificationPhoto;
        this.driverLicensePhoto = driverLicensePhoto;
        this.techPassportPhoto = techPassportPhoto;
        this.sex = sex;
        this.rating = rating;
    }
}
