package com.shpp.pzobenko.validation.application.validationipn.repository;

import com.shpp.pzobenko.validation.application.validationipn.gender.Genders;
import com.shpp.pzobenko.validation.application.validationipn.repository.CitizenOfUkraineRepo;
import com.shpp.pzobenko.validation.application.validationipn.ukrainianpeople.CitizenOfUkraine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CitizenOfUkraineRepoTest {
    @Autowired
    private CitizenOfUkraineRepo repositoryForTest;

    @AfterEach
    void tearDown() {
        repositoryForTest.deleteAll();
    }

    @Test
    void testExistMethodByIpnOnExist() {
        Long someIpn = 1000000089L;

        CitizenOfUkraine citizenToTestInDB = CitizenOfUkraine.builder()
                .ipn(someIpn)
                .firstName("SomeFirstName")
                .lastName("SomeLastName")
                .dateOfBirthday(LocalDateTime.now().minusYears(15))
                .genders(Genders.FEMALE)
                .build();

        repositoryForTest.save(citizenToTestInDB);

        assertTrue(repositoryForTest.existsByIpn(someIpn));
    }

    @Test
    void testExistMethodByIpnOnNotExist() {
        Long someIpn = 1111111198L;

        assertFalse(repositoryForTest.existsByIpn(someIpn));
    }
}