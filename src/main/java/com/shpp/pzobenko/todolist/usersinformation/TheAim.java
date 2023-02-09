package com.shpp.pzobenko.todolist.usersinformation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TheAim implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;
    @Id
    @GeneratedValue
    Long id;
    @Column(unique = true)
    private String nameOfAim;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private UserOfToDo userOfToDo;
    private String description;
    @Enumerated(EnumType.STRING)
    private StatusOfAim status;
    private LocalDateTime timeOfTheEndOfAim;

    @Override
    public String toString() {
        return "TheAim{" +
                "id=" + id +
                ", nameOfAim='" + nameOfAim + '\'' +
                ", userOfToDo=" + userOfToDo +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", timeOfTheEndOfAim=" + timeOfTheEndOfAim +
                '}';
    }
}
