package com.shpp.pzobenko.validation.application.todolist.assemblers;

import com.shpp.pzobenko.validation.application.todolist.controllers.ToDoListController;
import com.shpp.pzobenko.validation.application.todolist.usersinformation.StatusOfAim;
import com.shpp.pzobenko.validation.application.todolist.usersinformation.TheAim;
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

        if (theAim.getStatus() == StatusOfAim.PLANED) {

            orderModel.add(linkTo(methodOn(ToDoListController.class)
                    .statusToInProgress(theAim.getNameOfAim())).withRel("cancel"));
            orderModel.add(linkTo(methodOn(ToDoListController.class)
                    .statusToCanceled(theAim.getNameOfAim())).withRel("complete"));

        } else if (theAim.getStatus() == StatusOfAim.IN_PROCESS) {

            orderModel.add(linkTo(methodOn(ToDoListController.class)
                    .statusToDone(theAim.getNameOfAim())).withRel("cancel"));
            orderModel.add(linkTo(methodOn(ToDoListController.class)
                    .statusToCanceled(theAim.getNameOfAim())).withRel("complete"));

        } else {
            orderModel.add(linkTo(methodOn(ToDoListController.class)
                    .deleteTheAim(theAim.getNameOfAim())).withRel("delete"));
        }

        return orderModel;
    }
}