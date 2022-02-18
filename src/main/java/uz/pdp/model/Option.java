package uz.pdp.model;
//Sevinch Abdisattorova 02/18/2022 9:51 AM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "right_answer")
    private Boolean rightAnswer;

    @ManyToOne
    private Task task;

}
