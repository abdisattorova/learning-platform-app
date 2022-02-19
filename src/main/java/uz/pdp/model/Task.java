package uz.pdp.model;
//Sevinch Abdisattorova 02/18/2022 9:45 AM


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Lesson lesson;
    private String body;

}
