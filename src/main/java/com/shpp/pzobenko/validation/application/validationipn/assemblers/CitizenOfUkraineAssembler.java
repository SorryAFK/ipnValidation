package com.shpp.pzobenko.validation.application.validationipn.assemblers;

import com.shpp.pzobenko.validation.application.validationipn.controllers.CitizenOfUkraineController;
import com.shpp.pzobenko.validation.application.validationipn.ukrainianpeoples.CitizenOfUkraine;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CitizenOfUkraineAssembler implements RepresentationModelAssembler<CitizenOfUkraine
        , EntityModel<CitizenOfUkraine>> {

    @Override
    public EntityModel<CitizenOfUkraine> toModel(CitizenOfUkraine citizenOfUkraine) {
        return EntityModel.of(citizenOfUkraine, linkTo(methodOn(CitizenOfUkraineController.class)
                        .getCitizenOfUkraine(citizenOfUkraine.getIpn())).withSelfRel(),
                linkTo(methodOn(CitizenOfUkraineController.class)
                        .getAllCitizensOfUkraine()).withRel("citizen-of-ukraine"));
    }
}