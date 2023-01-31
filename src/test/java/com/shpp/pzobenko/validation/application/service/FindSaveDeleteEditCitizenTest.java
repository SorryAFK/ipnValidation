package com.shpp.pzobenko.validation.application.service;

import com.shpp.pzobenko.validation.application.exception.TheCitizenAlreadyExistException;
import com.shpp.pzobenko.validation.application.exception.TheCitizenOfUkraineNotFoundException;
import com.shpp.pzobenko.validation.application.gender.Genders;
import com.shpp.pzobenko.validation.application.repository.CitizenOfUkraineRepo;
import com.shpp.pzobenko.validation.application.ukrainianpeople.CitizenOfUkraine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindSaveDeleteEditCitizenTest {

    @Mock
    private CitizenOfUkraineRepo repository;

    private FindSaveDeleteEditCitizen serviceToTest;

    @BeforeEach
    void setUp() {
        serviceToTest = new FindSaveDeleteEditCitizen(repository);
    }

    @Test
    void getCitizenOfUkraineFromDBByIpn() {
        Long ipnForNewCitizen = 1111111181L;

        CitizenOfUkraine expected = CitizenOfUkraine.builder()
                .ipn(ipnForNewCitizen)
                .firstName("SomeFirstName")
                .lastName("SomeLastName")
                .dateOfBirthday(LocalDateTime.now().minusYears(5))
                .genders(Genders.FEMALE)
                .build();

        given(repository.findById(ipnForNewCitizen))
                .willReturn(Optional.of(expected));

        CitizenOfUkraine actual = serviceToTest.getCitizenOfUkraineFromDB(ipnForNewCitizen);

        assertEquals(expected,actual);
    }

    @Test
    void getCitizenOfUkraineFromDBButThrowException() {
        Long ipnForCitizenWhichNotExist = 1111111181L;

        assertThatThrownBy(() -> serviceToTest.getCitizenOfUkraineFromDB(ipnForCitizenWhichNotExist))
                .isInstanceOf(TheCitizenOfUkraineNotFoundException.class)
                .hasMessageContaining("The people with ipn \"" +
                        ipnForCitizenWhichNotExist + "\" not found.");
    }


    @Test
    void changeLastName() {
        Long ipnForNewCitizen = 1111111181L;
        String newLastName = "SomeLastName1";

        CitizenOfUkraine citizenToTestChangeLastName = CitizenOfUkraine.builder()
                .ipn(ipnForNewCitizen)
                .firstName("SomeFirstName")
                .lastName("SomeLastName")
                .dateOfBirthday(LocalDateTime.now().minusYears(5))
                .genders(Genders.FEMALE)
                .build();

        given(repository.findById(ipnForNewCitizen))
                .willReturn(Optional.of(citizenToTestChangeLastName));

        citizenToTestChangeLastName.setLastName(newLastName);

        given(repository.save(citizenToTestChangeLastName))
                .willReturn(citizenToTestChangeLastName);

        CitizenOfUkraine citizenButNewLastName =
                serviceToTest.changeLastName(citizenToTestChangeLastName.getIpn(),
                        newLastName);

        ArgumentCaptor<CitizenOfUkraine> argumentCaptor =
                ArgumentCaptor.forClass(CitizenOfUkraine.class);

        verify(repository).findById(citizenToTestChangeLastName.getIpn());

        verify(repository).save(argumentCaptor.capture());

        assertEquals(argumentCaptor.getValue(), citizenButNewLastName);
    }

    @Test
    void changeFirstName() {
        Long ipnForNewCitizen = 1111111181L;
        String newFirstName = "SomeFirstName1";

        CitizenOfUkraine citizenToTestChangeFirstName = CitizenOfUkraine.builder()
                .ipn(ipnForNewCitizen)
                .firstName("SomeFirstName")
                .lastName("SomeLastName")
                .dateOfBirthday(LocalDateTime.now().minusYears(5))
                .genders(Genders.FEMALE)
                .build();

        given(repository.findById(ipnForNewCitizen))
                .willReturn(Optional.of(citizenToTestChangeFirstName));

        citizenToTestChangeFirstName.setFirstName(newFirstName);

        given(repository.save(citizenToTestChangeFirstName))
                .willReturn(citizenToTestChangeFirstName);

        CitizenOfUkraine citizenButNewFirstName =
                serviceToTest.changeFirstName(citizenToTestChangeFirstName.getIpn(),
                        newFirstName);

        ArgumentCaptor<CitizenOfUkraine> argumentCaptor =
                ArgumentCaptor.forClass(CitizenOfUkraine.class);

        verify(repository).findById(citizenToTestChangeFirstName.getIpn());

        verify(repository).save(argumentCaptor.capture());

        assertEquals(argumentCaptor.getValue() ,citizenButNewFirstName);
    }

    @Test
    void getAllCitizens() {
        serviceToTest.getAllCitizens();

        verify(repository).findAll();
    }

    @Test
    void canSaveCitizenToDBTest() {
        CitizenOfUkraine citizenToTestSaveInDB = CitizenOfUkraine.builder()
                .ipn(1111111197L)
                .firstName("SomeFirstName")
                .lastName("SomeLastName")
                .dateOfBirthday(LocalDateTime.now().minusYears(5))
                .genders(Genders.MALE)
                .build();

        serviceToTest.saveCitizenToDB(citizenToTestSaveInDB);

        ArgumentCaptor<CitizenOfUkraine> argumentCaptor =
                ArgumentCaptor.forClass(CitizenOfUkraine.class);

        verify(repository).save(argumentCaptor.capture());

        CitizenOfUkraine citizenFromDB = argumentCaptor.getValue();

        assertEquals(citizenToTestSaveInDB, citizenFromDB);
    }

    @Test
    void willThrowBecauseAlreadyExist() {
        CitizenOfUkraine citizenToTestSaveInDB = CitizenOfUkraine.builder()
                .ipn(1111111197L)
                .firstName("SomeFirstName")
                .lastName("SomeLastName")
                .dateOfBirthday(LocalDateTime.now().minusYears(5))
                .genders(Genders.MALE)
                .build();

        given(repository.existsByIpn(citizenToTestSaveInDB.getIpn()))
                .willReturn(true);

       assertThatThrownBy(() -> serviceToTest.saveCitizenToDB(citizenToTestSaveInDB))
               .isInstanceOf(TheCitizenAlreadyExistException.class)
               .hasMessageContaining("The citizen with IPN: " +
                       citizenToTestSaveInDB.getIpn() + " already exist.");

       verify(repository, never()).save(any());
    }


    @Test
    void deleteCitizenFromDB() {
        Long ipnForCitizen = 1111111197L;
        CitizenOfUkraine citizenToTestDeleteFromDB = CitizenOfUkraine.builder()
                .ipn(ipnForCitizen)
                .firstName("SomeFirstName")
                .lastName("SomeLastName")
                .dateOfBirthday(LocalDateTime.now().minusYears(5))
                .genders(Genders.MALE)
                .build();

        given(repository.existsByIpn(ipnForCitizen))
                .willReturn(true);

        given(repository.findById(ipnForCitizen))
                .willReturn(Optional.of(citizenToTestDeleteFromDB));

        serviceToTest.deleteCitizenFromDB(ipnForCitizen);

        verify(repository).deleteById(ipnForCitizen);
    }
}