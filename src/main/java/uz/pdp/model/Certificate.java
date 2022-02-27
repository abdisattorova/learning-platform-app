package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity(name = "certificates")
@PackagePrivate
public class Certificate {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    Course course;

    @ManyToOne
    User user;


}
