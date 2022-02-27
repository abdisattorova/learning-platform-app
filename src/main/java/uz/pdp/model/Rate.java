package uz.pdp.model;
//Sevinch Abdisattorova 02/18/2022 9:59 AM


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "rates")
public class Rate {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Course course;
    @ManyToOne
    private User user;

    private Double rate;
}
