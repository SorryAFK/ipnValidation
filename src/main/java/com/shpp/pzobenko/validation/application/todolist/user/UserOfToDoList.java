package com.shpp.pzobenko.validation.application.todolist.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class UserOfToDoList {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    @Getter(AccessLevel.PRIVATE)
    private String password;
    @OneToMany(mappedBy = "userName", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TheAim> toDo;
}
