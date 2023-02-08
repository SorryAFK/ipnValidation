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
    private String nameOfAim;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private UserOfToDo userOfToDo;
    private String description;
    @Enumerated
    private StatusOfAim status;
    private LocalDateTime timeOfTheEndOfAim;

    @Override
    public String toString() {
        return "TheAim{" +
                "nameOfAim='" + nameOfAim + '\'' +
                ", userOfToDo=" + userOfToDo +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", timeOfTheEndOfAim=" + timeOfTheEndOfAim +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TheAim theAim = (TheAim) o;

        if (!nameOfAim.equals(theAim.nameOfAim)) return false;
        if (!userOfToDo.equals(theAim.userOfToDo)) return false;
        if (!description.equals(theAim.description)) return false;
        if (status != theAim.status) return false;
        return timeOfTheEndOfAim.equals(theAim.timeOfTheEndOfAim);
    }

    @Override
    public int hashCode() {
        int result = nameOfAim.hashCode();
        result = 31 * result + userOfToDo.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + timeOfTheEndOfAim.hashCode();
        return result;
    }
}
