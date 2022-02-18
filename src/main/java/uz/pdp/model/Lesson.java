package uz.pdp.model;
//Sevinch Abdisattorova 02/13/2022 9:21 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "lessons")
public class Lesson {
    @Id
    private Integer id;
    private String name;

    @ManyToOne
    private Module module;

    private String body;

    private String video_link;
}
