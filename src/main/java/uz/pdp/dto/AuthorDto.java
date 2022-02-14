package uz.pdp.dto;
//Sevinch Abdisattorova 02/11/2022 9:16 AM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDto {
    private int id;
    private String fullName;

}
