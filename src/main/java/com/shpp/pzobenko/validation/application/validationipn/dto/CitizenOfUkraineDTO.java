package com.shpp.pzobenko.validation.application.validationipn.dto;

import com.shpp.pzobenko.validation.application.validationipn.gender.Genders;
import com.shpp.pzobenko.validation.application.validationipn.validator.AreCorrectIPNAndMakeSureThatLengthIs10;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitizenOfUkraineDTO {
    @NotNull
    @AreCorrectIPNAndMakeSureThatLengthIs10
    private Long ipn;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Genders genders;
    @NotNull
    @Past
    private LocalDateTime dateOfBirthday;

    @Override
    public String toString() {
        return "CitizenOfUkraineDTO{" +
                "ipn=" + ipn +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + genders +
                ", dateOfBirthday=" + dateOfBirthday +
                '}';
    }
}
