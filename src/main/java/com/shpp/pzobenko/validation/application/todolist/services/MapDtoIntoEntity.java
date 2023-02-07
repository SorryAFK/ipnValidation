package com.shpp.pzobenko.validation.application.todolist.services;

import com.shpp.pzobenko.validation.application.todolist.dtos.TheAimDto;
import com.shpp.pzobenko.validation.application.todolist.usersinformation.TheAim;
import org.springframework.stereotype.Service;

@Service
public class MapDtoIntoEntity {
    private MapDtoIntoEntity() {
        //
    }

    public static TheAim mapDtoToAimEntity(TheAimDto dto) {
        return TheAim.builder()
                .nameOfAim(dto.getNameOfAim())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .timeOfTheEndOfAim(dto.getTimeOfTheEndOfAim())
                .build();
    }
}
