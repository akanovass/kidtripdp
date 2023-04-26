package kz.iitu.kidtirp.model.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class DriverRequest {

    private Long id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    private String fullName;
    @NotBlank
    private String gmail;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Size(max = 120)
    private String password;
    private String photoUrl;
    @NotBlank
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

}
