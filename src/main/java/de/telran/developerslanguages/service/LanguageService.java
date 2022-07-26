package de.telran.developerslanguages.service;

import de.telran.developerslanguages.dto.request.LanguageRequestDTO;
import de.telran.developerslanguages.dto.response.LanguageResponseDTO;
import de.telran.developerslanguages.entity.Language;

import java.util.List;

public interface LanguageService {

    List<Language> saveLanguages(List<LanguageRequestDTO> list);

    LanguageResponseDTO getMostPopular();
}
