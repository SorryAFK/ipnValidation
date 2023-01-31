package com.shpp.pzobenko.validation.application.service;

import com.shpp.pzobenko.validation.application.dto.CitizenOfUkraineDTO;
import com.shpp.pzobenko.validation.application.ukrainianpeople.CitizenOfUkraine;

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
