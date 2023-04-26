package kz.iitu.kidtirp.model.dto.request;

import kz.iitu.kidtirp.model.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildRequest {
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
    private Long parentId;
    private Date birthDate;
    private Gender gender;
    private String address;
    private String schoolName;
    private Integer classN;
    private String medicalConditions;
    private String photoUrl;
}
