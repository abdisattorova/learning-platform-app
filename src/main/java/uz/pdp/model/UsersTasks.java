package uz.pdp.model;
//Sevinch Abdisattorova 02/20/2022 7:15 PM

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users_tasks")
@PackagePrivate
public class UsersTasks {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    User user;

    @ManyToOne
    Task task;

    @Column(name = "is_completed")
    Boolean isCompleted ;


    public UsersTasks(User user, Task task, Boolean isCompleted) {
        this.user = user;
        this.task = task;
        this.isCompleted = isCompleted;
    }

}
