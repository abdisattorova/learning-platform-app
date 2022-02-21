package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "image_url")
    private String imageUrl;
    @ManyToMany
    @JoinTable(
            name = "courses_users",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id")})

    private List<User> authorList;

    public Course(Integer id, String name, String description, Boolean isActive, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
    }

    public Course(Integer id) {
        this.id = id;
    }
}
