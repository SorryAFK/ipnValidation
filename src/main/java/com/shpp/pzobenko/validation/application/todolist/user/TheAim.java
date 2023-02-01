package com.shpp.pzobenko.validation.application.todolist.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TheAim {
    @Id
    @GeneratedValue
    Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    UserOfToDoList user;
    String name;
    String description;

    StatusOfAim status;
    LocalDateTime timeOfTheEndOfAim;


}
