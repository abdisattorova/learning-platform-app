package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int id;
    private String fullName;
    private String username;
    private String password;
    private String role;
    private String imageUrl;

}
