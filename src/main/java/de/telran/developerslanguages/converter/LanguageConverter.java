package de.telran.developerslanguages.converter;

import de.telran.developerslanguages.dto.response.LanguageResponseDTO;
import de.telran.developerslanguages.entity.Language;

public class LanguageConverter {

    public static LanguageResponseDTO convertLanguageToResponseDTO(Language language) {
        return LanguageResponseDTO
                .builder()
                .id(language.getId())
                .name(language.getName())
                .usages(language.getCountOfUsage())
                .build();
    }
}
