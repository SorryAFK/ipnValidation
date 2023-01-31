package com.shpp.pzobenko.validation.application.ukrainianpeople;

import com.shpp.pzobenko.validation.application.gender.Genders;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitizenOfUkraine {
    @Id
    private Long ipn;
    private String firstName;
    private String lastName;
    private Genders genders;
    private LocalDateTime dateOfBirthday;

    @Override
    public String toString() {
        return "CitizenOfUkraine{" +
                "ipn=" + ipn +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + genders +
                ", dateOfBirthday=" + dateOfBirthday +
                '}';
    }
}