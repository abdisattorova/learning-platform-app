package uz.pdp.dto;
//Sevinch Abdisattorova 02/19/2022 3:50 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.Module;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonDto {
    private Integer id;
    private String name;
    private int moduleId;
    private String body;
    private String video_link;
}
