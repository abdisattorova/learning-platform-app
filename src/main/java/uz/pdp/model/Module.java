package uz.pdp.model;
//Sevinch Abdisattorova 02/13/2022 9:20 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Module {
    private int id;
    private String name;
    private int courseId;

}
