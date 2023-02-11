package com.shpp.pzobenko.todolist.controllers;

import com.shpp.pzobenko.todolist.assemblers.ToDoListAssemblerForAim;
import com.shpp.pzobenko.todolist.assemblers.UserOfTodoListAssembler;
import com.shpp.pzobenko.todolist.services.GetCurrentUserService;
import com.shpp.pzobenko.todolist.services.UserOfToDoService;
import com.shpp.pzobenko.todolist.usersinformation.StatusOfAim;
import com.shpp.pzobenko.todolist.usersinformation.TheAim;
import com.shpp.pzobenko.todolist.usersinformation.UserOfToDo;
import com.shpp.pzobenko.todolist.dtos.TheAimDto;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.shpp.pzobenko.todolist.services.MapDtoIntoEntity.*;
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
        return userAssembler.toModel(service.getUserOfToDo(GetCurrentUserService.getCurrentUsername()));
    }


    @GetMapping("/admins-rules-only/all")
    public CollectionModel<EntityModel<UserOfToDo>> allUsers() {
        List<EntityModel<UserOfToDo>> entityModelList = service.
                getAllUsersInThisAppButOnlyAdminRule(GetCurrentUserService.getCurrentUsername()).stream()
                .map(userAssembler::toModel).
                collect(Collectors.toList());
        return CollectionModel.of(entityModelList,
                linkTo(methodOn(ToDoListController.class).allUsers()).withSelfRel());
    }
    @PostMapping("/add-new-to-do")
    public ResponseEntity<EntityModel<TheAim>> addNewAim(@RequestBody @Valid TheAimDto dto) {
        return ResponseEntity
                .created(linkTo(methodOn(ToDoListController.class).one(dto.getNameOfAim())).toUri())
                .body(aimAssembler.toModel(service.addNewAim(mapDtoToAimEntity(dto), GetCurrentUserService.getCurrentUsername())));
    }

    @PutMapping("/{nameOfAim}")
    public ResponseEntity<EntityModel<TheAim>> changeStatus(@PathVariable String nameOfAim,
                                                            @RequestParam(name = "status")
                                                            StatusOfAim newStatus) {
        return ResponseEntity.ok(aimAssembler.toModel(service.saveNewStatusOrThrowException(nameOfAim, newStatus)));
    }
    @DeleteMapping("/{nameOfAim}/delete")
    public ResponseEntity<EntityModel<TheAim>> deleteTheAim(@PathVariable String nameOfAim) {
        return ResponseEntity
                .created(linkTo(methodOn(ToDoListController.class).all()).toUri())
                .body(aimAssembler.toModel(service.deleteAim(nameOfAim)));
    }
}
