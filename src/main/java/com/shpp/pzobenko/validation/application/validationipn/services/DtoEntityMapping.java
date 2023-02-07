package com.shpp.pzobenko.validation.application.validationipn.services;

import com.shpp.pzobenko.validation.application.validationipn.dtos.CitizenOfUkraineDTO;
import com.shpp.pzobenko.validation.application.validationipn.ukrainianpeoples.CitizenOfUkraine;

public class DtoEntityMapping {

    private DtoEntityMapping() {
        //private because static method
    }

    public static CitizenOfUkraine dtoToEntity(CitizenOfUkraineDTO citizenOfUkraineDTO) {
        return CitizenOfUkraine.builder()
                .ipn(citizenOfUkraineDTO.getIpn())
                .firstName(citizenOfUkraineDTO.getFirstName())
                .lastName(citizenOfUkraineDTO.getLastName())
                .genders(citizenOfUkraineDTO.getGenders())
                .dateOfBirthday(citizenOfUkraineDTO.getDateOfBirthday())
                .build();

    }
}
