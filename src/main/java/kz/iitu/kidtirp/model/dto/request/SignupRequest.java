package kz.iitu.kidtirp.model.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kz.iitu.kidtirp.model.entity.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

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

    @NotNull
    private ERole role;

    public SignupRequest(String username, String fullName, String gmail, String phoneNumber, String password, ERole role) {
        this.username = username;
        this.fullName = fullName;
        this.gmail = gmail;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }
}
