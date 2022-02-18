package uz.pdp.model;
//Sevinch Abdisattorova 02/18/2022 12:04 PM


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "discussions")
public class Discussion {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User user;

    private String message;

    @ManyToOne
    private Lesson lesson;
}

