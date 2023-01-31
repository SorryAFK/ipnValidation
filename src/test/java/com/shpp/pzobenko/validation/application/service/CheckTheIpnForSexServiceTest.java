package com.shpp.pzobenko.validation.application.service;

import com.shpp.pzobenko.validation.application.gender.Genders;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckTheIpnForSexServiceTest {

    @Test
    void testAreIPNValidBySexMethodOnCorrectValues() {
        Long someIpn = 1111111198L;
        Genders someGender = Genders.FEMALE;
        assertTrue(CheckTheIpnForSexService.areIPNValidBySex(someIpn,someGender));
    }

    @Test
    void testAreIPNValidBySexMethodOnWrongValues() {
        Long someIpn = 1000000011L;
        Genders someGender = Genders.MALE;
        assertFalse(CheckTheIpnForSexService.areIPNValidBySex(someIpn,someGender));
    }
}