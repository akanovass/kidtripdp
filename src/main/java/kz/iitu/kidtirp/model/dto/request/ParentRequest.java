package kz.iitu.kidtirp.model.dto.request;

import kz.iitu.kidtirp.model.entity.Child;
import kz.iitu.kidtirp.model.entity.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentRequest {

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

    private String numberCard;

    private String ownerNameCard;

    private Date cardExpireDate;

    private String cvv;


}
