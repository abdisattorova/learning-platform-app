package uz.pdp.model;
//Sevinch Abdisattorova 02/13/2022 9:21 PM

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "lessons")
public class Lesson {
    @Id
    private Integer id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Module module;

    private String body;

    private String video_link;
}
