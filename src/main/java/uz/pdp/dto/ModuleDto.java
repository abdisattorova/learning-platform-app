package uz.pdp.dto;
//Sevinch Abdisattorova 02/13/2022 9:22 PM


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.Lesson;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModuleDto {
    private int id;
    private String name;
    private List<Lesson> lessons;
}
