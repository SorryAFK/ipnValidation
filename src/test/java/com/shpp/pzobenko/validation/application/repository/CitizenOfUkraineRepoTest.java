package com.shpp.pzobenko.validation.application.repository;

import com.shpp.pzobenko.validation.application.gender.Genders;
import com.shpp.pzobenko.validation.application.ukrainianpeople.CitizenOfUkraine;
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
        Long someIpn = 1111111198L;

        CitizenOfUkraine citizenToTestInDB = CitizenOfUkraine.builder()
                .ipn(1111111198L)
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