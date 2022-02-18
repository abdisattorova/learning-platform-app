package uz.pdp.model;
//Sevinch Abdisattorova 02/13/2022 9:20 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "modules")
public class Module {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @ManyToOne
    private Course course;



}
