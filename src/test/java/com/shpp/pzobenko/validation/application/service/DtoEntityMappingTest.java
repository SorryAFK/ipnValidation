package com.shpp.pzobenko.validation.application.service;

import com.shpp.pzobenko.validation.application.dto.CitizenOfUkraineDTO;
import com.shpp.pzobenko.validation.application.gender.Genders;
import com.shpp.pzobenko.validation.application.ukrainianpeople.CitizenOfUkraine;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DtoEntityMappingTest {

    @Test
    void dtoToEntity() {
        CitizenOfUkraineDTO dtoToTest = CitizenOfUkraineDTO.builder()
                .ipn(1111111188L)
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