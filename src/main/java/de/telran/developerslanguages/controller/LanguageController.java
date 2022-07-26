package de.telran.developerslanguages.controller;

import de.telran.developerslanguages.dto.response.LanguageResponseDTO;
import de.telran.developerslanguages.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LanguageController {

    private final LanguageService service;

    @GetMapping("/api/languages")
    public LanguageResponseDTO getMostPopular() {
        return service.getMostPopular();
    }
}

//TODO
// deploy heroku
