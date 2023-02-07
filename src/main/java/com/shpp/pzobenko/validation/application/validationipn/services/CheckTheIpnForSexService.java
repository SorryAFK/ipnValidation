package com.shpp.pzobenko.validation.application.validationipn.services;

import com.shpp.pzobenko.validation.application.validationipn.genders.Genders;
import org.springframework.stereotype.Service;

@Service
public class CheckTheIpnForSexService {
    private CheckTheIpnForSexService() {
    }

    private static final int LOCATION_OF_THE_NUMBER_FOR_SEX = 8;

    public static boolean areIPNValidBySex(Long ipn, Genders genders) {
        int sexValueFromIPN = getTheNumberWithSexValue(ipn);
        if(sexValueFromIPN % 2 != 0 && genders.equals(Genders.MALE)) {
            return true;
        } else return sexValueFromIPN % 2 == 0 && genders.equals(Genders.FEMALE);
    }

    private static int getTheNumberWithSexValue(Long ipn) {
        String[] ipnOnString =  ipn.toString().split("");

        int[] ipnOnLongArray = new int[ipnOnString.length];
        for(int i = 0; i < ipnOnLongArray.length; i++) {
            ipnOnLongArray[i] = Integer.parseInt(ipnOnString[i]);
        }
        return ipnOnLongArray[LOCATION_OF_THE_NUMBER_FOR_SEX];
    }

}
