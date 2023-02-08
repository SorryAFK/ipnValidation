package com.shpp.pzobenko.todolist.dtos;

import com.shpp.pzobenko.todolist.usersinformation.StatusOfAim;
import com.shpp.pzobenko.todolist.validators.IsNewAimStatusArePlaned;
import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheAimDto {
    @NotNull
    private String nameOfAim;
    @NotNull
    private String description;
    @IsNewAimStatusArePlaned
    private StatusOfAim status;
    @NotNull
    @Future
    private LocalDateTime timeOfTheEndOfAim;
}
