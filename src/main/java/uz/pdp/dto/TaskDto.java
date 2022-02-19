package uz.pdp.dto;
//Sevinch Abdisattorova 02/18/2022 1:50 PM


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import uz.pdp.model.Lesson;
import uz.pdp.model.Option;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate

public class TaskDto {
    Integer id;
    Integer lessonId;
    String lessonName;
    String body;
    String title;
    String[] answers;
    List<Option> options;
}
