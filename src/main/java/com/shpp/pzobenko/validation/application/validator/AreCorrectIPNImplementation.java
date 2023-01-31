package com.shpp.pzobenko.validation.application.validator;

import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Log4j2
public class AreCorrectIPNImplementation implements ConstraintValidator<AreCorrectIPNAndMakeSureThatLengthIs10, Long> {
    /**
     * The length of ipn number always 10
     */
    private static final int IPN_LENGTH = 10;

    @Override
    public void initialize(AreCorrectIPNAndMakeSureThatLengthIs10 constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long ipn, ConstraintValidatorContext constraintValidatorContext) {
        long[] ipnOnArray = makeFromLongIPNTheArrayOfLong(ipn);
        if(ipnOnArray.length != IPN_LENGTH) {
            return false;
        }
        long ipnLastNumberByFormula = calculateLastNumberInIpn(ipnOnArray);
        log.info("expected {}, actual {}", ipnOnArray[IPN_LENGTH-1], ipnLastNumberByFormula);
        return ipnLastNumberByFormula == ipnOnArray[IPN_LENGTH - 1];
    }

    /**
     * For example our IPN number looks like 'A B C D E F G H I LastNumberOfIPN'
     * So the formula for last number of ipn ->
     * LastNumberOfIPN = A*(-1) + B*5 + C*7 + D*9 + E*4 + F*6 + G*10 + H*5 + I*7
     * After the calculation the result needs to divide into 11 and take the last number after dot
     *
     * @param ipnOnArray ipn for validation in array variant.
     * @return the last digit from ipn which was calculated by formula.
     */
    private long calculateLastNumberInIpn(long[] ipnOnArray) {
        long ipnLastNumberByFormula = (ipnOnArray[0] * (-1)) + (ipnOnArray[1] * 5) +
                (ipnOnArray[2] * 7) + (ipnOnArray[3] * 9) + (ipnOnArray[4] * 4) + (ipnOnArray[5] * 6) +
                (ipnOnArray[6] * 10) + (ipnOnArray[7] * 5) + (ipnOnArray[8] * 7);
        ipnLastNumberByFormula = ipnLastNumberByFormula % 11;
        return Long.parseLong(Long.toString(ipnLastNumberByFormula).split("")
                [Long.toString(ipnLastNumberByFormula).split("").length -1]);
    }

    private long[] makeFromLongIPNTheArrayOfLong(Long ipn) {
        String[] ipnOnString = ipn.toString().split("");

        long[] ipnOnArray = new long[ipnOnString.length];

        for (int i = 0; i < ipnOnString.length; i++) {
            ipnOnArray[i] = Long.parseLong(ipnOnString[i]);
        }
        return ipnOnArray;
    }
}
