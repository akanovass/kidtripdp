package kz.iitu.kidtirp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.iitu.kidtirp.model.entity.enums.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
        })
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ERole role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
