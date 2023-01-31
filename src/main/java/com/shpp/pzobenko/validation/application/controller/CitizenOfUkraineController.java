package com.shpp.pzobenko.validation.application.controller;

import com.shpp.pzobenko.validation.application.assembler.CitizenOfUkraineAssembler;
import com.shpp.pzobenko.validation.application.dto.CitizenOfUkraineDTO;
import com.shpp.pzobenko.validation.application.service.DtoEntityMapping;
import com.shpp.pzobenko.validation.application.service.FindSaveDeleteEditCitizen;
import com.shpp.pzobenko.validation.application.ukrainianpeople.CitizenOfUkraine;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@Log4j2
@AllArgsConstructor
public class CitizenOfUkraineController {

    private final CitizenOfUkraineAssembler assembler;
    private final FindSaveDeleteEditCitizen findOrSaveCitizen;


    @GetMapping("/citizen-of-ukraine/{ipn}")
    public EntityModel<CitizenOfUkraine> getCitizenOfUkraine(@PathVariable Long ipn) {
        log.info("Get citizen from DB by ipn {}", ipn);
        return assembler.toModel(findOrSaveCitizen.getCitizenOfUkraineFromDB(ipn));
    }

    @GetMapping("/citizen-of-ukraine")
    public CollectionModel<EntityModel<CitizenOfUkraine>> getAllCitizensOfUkraine() {
        log.info("Getting all citizens from DB.");
        return CollectionModel.of(findOrSaveCitizen.getAllCitizens()
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(CitizenOfUkraineController.class).getAllCitizensOfUkraine()).withSelfRel());
    }

    @PostMapping("/citizen-of-ukraine")
    public ResponseEntity<EntityModel<CitizenOfUkraine>> newCitizen(@RequestBody @Valid CitizenOfUkraineDTO dto) {
        return ResponseEntity
                .created(linkTo(methodOn(CitizenOfUkraineController.class)
                        .getCitizenOfUkraine(dto.getIpn())).toUri())
                .body(assembler.toModel(findOrSaveCitizen.saveCitizenToDB(DtoEntityMapping.dtoToEntity(dto))));
    }

    @PutMapping("/citizen-of-ukraine/{ipn}/change-lastname/{lastName}")
    public ResponseEntity<EntityModel<CitizenOfUkraine>> changeLastName(@PathVariable Long ipn
            , @PathVariable String lastName) {
        log.info("Changing last name on citizen with ipn {} into {}", ipn, lastName);
        return ResponseEntity.ok(assembler.toModel(findOrSaveCitizen.changeLastName(ipn, lastName)));
    }

    @PutMapping("/citizen-of-ukraine/{ipn}/change-firstname/{firstName}")
    public ResponseEntity<EntityModel<CitizenOfUkraine>> changeFirstName(@PathVariable Long ipn
            , @PathVariable String firstName) {
        log.info("Changing first name on citizen with ipn {} into {}", ipn, firstName);
        return ResponseEntity.ok(assembler.toModel(findOrSaveCitizen.changeFirstName(ipn, firstName)));
    }

    @DeleteMapping("/citizen-of-ukraine/{ipn}/delete")
    public ResponseEntity<EntityModel<CitizenOfUkraine>> deleteCitizenByIpn(@PathVariable Long ipn) {
        log.info("Deleting citizen with ipn {} from DB", ipn);
        return ResponseEntity.ok(assembler.toModel(findOrSaveCitizen.deleteCitizenFromDB(ipn)));
    }
}
