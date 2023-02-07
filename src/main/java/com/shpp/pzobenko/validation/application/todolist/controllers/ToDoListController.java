package com.shpp.pzobenko.validation.application.todolist.controllers;

import com.shpp.pzobenko.validation.application.todolist.assemblers.ToDoListAssemblerForAim;
import com.shpp.pzobenko.validation.application.todolist.assemblers.UserOfTodoListAssembler;
import com.shpp.pzobenko.validation.application.todolist.services.UserOfToDoService;
import com.shpp.pzobenko.validation.application.todolist.dtos.TheAimDto;
import com.shpp.pzobenko.validation.application.todolist.usersinformation.StatusOfAim;
import com.shpp.pzobenko.validation.application.todolist.usersinformation.TheAim;
import com.shpp.pzobenko.validation.application.todolist.usersinformation.UserOfToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.shpp.pzobenko.validation.application.todolist.services.GetCurrentUserService.*;
import static com.shpp.pzobenko.validation.application.todolist.services.MapDtoIntoEntity.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/to-do-list")
@RequiredArgsConstructor
public class ToDoListController {
    private final UserOfToDoService service;
    private final ToDoListAssemblerForAim aimAssembler;
    private final UserOfTodoListAssembler userAssembler;

    @GetMapping("/all-to-do")
    public CollectionModel<EntityModel<TheAim>> all() {
        List<EntityModel<TheAim>> listOfAims = service.getAllAims().stream()
                .map(aimAssembler::toModel).
                collect(Collectors.toList());
        return CollectionModel.of(listOfAims,
                linkTo(methodOn(ToDoListController.class).all()).withSelfRel());
    }

    @GetMapping("/get-one/{nameOfAim}")
    public EntityModel<TheAim> one(@PathVariable String nameOfAim) {
        return aimAssembler.toModel(service.getAimByName(nameOfAim));
    }

    @GetMapping("/user-info/")
    public EntityModel<UserOfToDo> userInfo() {
        return userAssembler.toModel(service.getUserOfToDo(getCurrentUsername()));
    }


    @GetMapping("/admins-rules-only/all")
    public CollectionModel<EntityModel<UserOfToDo>> allUsers() {
        List<EntityModel<UserOfToDo>> entityModelList = service.
                getAllUsersInThisAppButOnlyAdminRule(getCurrentUsername()).stream()
                .map(userAssembler::toModel).
                collect(Collectors.toList());
        return CollectionModel.of(entityModelList,
                linkTo(methodOn(ToDoListController.class).allUsers()).withSelfRel());
    }
    @PostMapping("/add-new-to-do")
    public ResponseEntity<EntityModel<TheAim>> addNewAim(@RequestBody @Valid TheAimDto dto) {
        return ResponseEntity
                .created(linkTo(methodOn(ToDoListController.class).one(dto.getNameOfAim())).toUri())
                .body(aimAssembler.toModel(service.addNewAim(mapDtoToAimEntity(dto), getCurrentUsername())));
    }

    @PutMapping("/{nameOfAim}/in-progress")
    public ResponseEntity<EntityModel<TheAim>> statusToInProgress(@PathVariable String nameOfAim) {
        return ResponseEntity.ok(aimAssembler.toModel(service.changeStatusOfAim(nameOfAim, StatusOfAim.IN_PROCESS, nameOfAim)));
    }

    @PutMapping("/{nameOfAim}/done")
    public ResponseEntity<EntityModel<TheAim>> statusToDone(@PathVariable String nameOfAim) {
        return ResponseEntity.ok(aimAssembler.toModel(service.changeStatusOfAim(nameOfAim, StatusOfAim.DONE, nameOfAim)));
    }

    @PutMapping("/{nameOfAim}/canceled")
    public ResponseEntity<EntityModel<TheAim>> statusToCanceled(@PathVariable String nameOfAim) {
        return ResponseEntity.ok(aimAssembler.toModel(service.changeStatusOfAim(nameOfAim, StatusOfAim.CANCELED, nameOfAim)));
    }

    @DeleteMapping("/{nameOfAim}/delete")
    public ResponseEntity<EntityModel<TheAim>> deleteTheAim(@PathVariable String nameOfAim) {
        return ResponseEntity
                .created(linkTo(methodOn(ToDoListController.class).all()).toUri())
                .body(aimAssembler.toModel(service.deleteAim(nameOfAim)));
    }
}
