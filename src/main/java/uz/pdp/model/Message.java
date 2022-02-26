package uz.pdp.model;
//Sevinch Abdisattorova 02/26/2022 10:29 PM


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
@Entity(name = "messages")
public class Message {
    @Id
    @GeneratedValue
    Integer id;

    String message;

    @ManyToOne
    User sender;

    @ManyToOne
    User receiver;

    LocalDateTime created_at;

//    @Column(columnDefinition = "false")
    Boolean isRead;
}
