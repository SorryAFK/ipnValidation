package com.shpp.pzobenko.todolist.assemblers;

import com.shpp.pzobenko.todolist.usersinformation.StatusOfAim;
import com.shpp.pzobenko.todolist.usersinformation.TheAim;
import com.shpp.pzobenko.todolist.controllers.ToDoListController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ToDoListAssemblerForAim implements RepresentationModelAssembler<TheAim, EntityModel<TheAim>> {

    @Override
    public EntityModel<TheAim> toModel(TheAim theAim) {
        EntityModel<TheAim> orderModel = EntityModel.of(theAim,
                linkTo(methodOn(ToDoListController.class).one(theAim.getNameOfAim())).withSelfRel());
        linkTo(methodOn(ToDoListController.class).all()).withRel("To Do's");

//
//
//            orderModel.add(linkTo(methodOn(ToDoListController.class)
//                    .changeStatus(theAim.getNameOfAim(), theAim.getStatus()).withRel("cancel"));



        return orderModel;
    }
}