package uz.pdp.dto;
//Sevinch Abdisattorova 02/18/2022 1:50 PM


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import uz.pdp.model.Lesson;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate

public class TaskDto {
    Integer id;
    String title;
    Integer lessonId;
    String lessonName;
    String body;
}
