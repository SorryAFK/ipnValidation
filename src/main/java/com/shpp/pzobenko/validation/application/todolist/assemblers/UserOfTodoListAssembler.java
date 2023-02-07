package com.shpp.pzobenko.validation.application.todolist.assemblers;

import com.shpp.pzobenko.validation.application.todolist.controllers.ToDoListController;
import com.shpp.pzobenko.validation.application.todolist.usersinformation.UserOfToDo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class UserOfTodoListAssembler implements RepresentationModelAssembler<UserOfToDo, EntityModel<UserOfToDo>> {
    @Override
    public EntityModel<UserOfToDo> toModel(UserOfToDo userOfToDo) {
        return EntityModel.of(userOfToDo,
                linkTo(methodOn(ToDoListController.class).userInfo()).withSelfRel(),
                linkTo(methodOn(ToDoListController.class).all()).withRel("ToDo's"));
    }
}
