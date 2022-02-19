package uz.pdp.model;
//Sevinch Abdisattorova 02/13/2022 9:20 PM

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "modules")
public class Module {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @ManyToOne
    private Course course;

    public Module(Integer id) {
        this.id = id;
    }
}
