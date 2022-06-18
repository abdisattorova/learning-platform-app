package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "full_name")
    @Size(min = 2, message = "Full name should contain at least two letters")
    private String fullName;
    private String username;
    private Boolean is_blocked;
    //    @NotBlank
    @Size(min = 5, message = "Password length should be at least 5")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "image_url")
    private String imageUrl;


    public User(Integer id) {
        this.id = id;
    }
}
