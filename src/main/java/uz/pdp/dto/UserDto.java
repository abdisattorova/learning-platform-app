package uz.pdp.dto;
//Sevinch Abdisattorova 02/27/2022 1:51 AM


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Integer id;
    private String fullName;
    private String username;
    private String password;
    private Role role;
    private String imageUrl;
    private Boolean is_blocked;
    private Integer unreadMsgs;
}
