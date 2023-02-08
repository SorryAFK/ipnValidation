package com.shpp.pzobenko.todolist.services;

import com.shpp.pzobenko.todolist.usersinformation.TheAim;
import com.shpp.pzobenko.todolist.dtos.TheAimDto;
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
