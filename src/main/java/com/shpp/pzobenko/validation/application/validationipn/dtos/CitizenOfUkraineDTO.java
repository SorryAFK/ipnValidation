package com.shpp.pzobenko.validation.application.validationipn.dtos;

import com.shpp.pzobenko.validation.application.validationipn.genders.Genders;
import com.shpp.pzobenko.validation.application.validationipn.validators.AreCorrectIPNAndMakeSureThatLengthIs10;
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
