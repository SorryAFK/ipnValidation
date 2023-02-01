package com.shpp.pzobenko.validation.application.validationipn.service;

import com.shpp.pzobenko.validation.application.validationipn.gender.Genders;
import com.shpp.pzobenko.validation.application.validationipn.service.CheckTheIpnForSexService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckTheIpnForSexServiceTest {

    @Test
    void testAreIPNValidBySexMethodOnCorrectValues() {
        Long someIpn = 1000000089L;
        Genders someGender = Genders.FEMALE;
        assertTrue(CheckTheIpnForSexService.areIPNValidBySex(someIpn,someGender));
    }

    @Test
    void testAreIPNValidBySexMethodOnWrongValues() {
        Long someIpn = 1000000021L;
        Genders someGender = Genders.MALE;
        assertFalse(CheckTheIpnForSexService.areIPNValidBySex(someIpn,someGender));
    }
}