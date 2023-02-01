package com.shpp.pzobenko.validation.application.validationipn.service;

import com.shpp.pzobenko.validation.application.validationipn.dto.CitizenOfUkraineDTO;
import com.shpp.pzobenko.validation.application.validationipn.gender.Genders;
import com.shpp.pzobenko.validation.application.validationipn.service.DtoEntityMapping;
import com.shpp.pzobenko.validation.application.validationipn.ukrainianpeople.CitizenOfUkraine;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DtoEntityMappingTest {

    @Test
    void dtoToEntity() {
        CitizenOfUkraineDTO dtoToTest = CitizenOfUkraineDTO.builder()
                .ipn(1000000089L)
                .firstName("SomeFirstName")
                .lastName("SomeLastName")
                .dateOfBirthday(LocalDateTime.now().minusYears(5))
                .genders(Genders.FEMALE)
                .build();
        CitizenOfUkraine testCitizen = DtoEntityMapping.dtoToEntity(dtoToTest);
        assertEquals(dtoToTest.getIpn(),testCitizen.getIpn());
        assertEquals(dtoToTest.getFirstName(),testCitizen.getFirstName());
        assertEquals(dtoToTest.getLastName(),testCitizen.getLastName());
        assertEquals(dtoToTest.getDateOfBirthday(), testCitizen.getDateOfBirthday());
        assertEquals(dtoToTest.getGenders(),testCitizen.getGenders());
    }
}