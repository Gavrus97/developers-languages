package de.telran.developerslanguages.converter;

import de.telran.developerslanguages.dto.response.DeveloperResponseDTO;
import de.telran.developerslanguages.entity.Developer;

public class DeveloperConverter {

    public static DeveloperResponseDTO convertDeveloperIntoResponseDTO(Developer developer){
        return DeveloperResponseDTO
                .builder()
                .name(developer.getName())
                .languages(developer.getLanguages())
                .build();
    }
}
