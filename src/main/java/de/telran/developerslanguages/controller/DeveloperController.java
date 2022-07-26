package de.telran.developerslanguages.controller;

import de.telran.developerslanguages.dto.request.DeveloperRequestDTO;
import de.telran.developerslanguages.dto.request.LanguageRequestDTO;
import de.telran.developerslanguages.dto.response.DeveloperResponseDTO;
import de.telran.developerslanguages.service.DeveloperService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class DeveloperController {

    private final DeveloperService service;

    @PostMapping("/api")
    public DeveloperResponseDTO create(@RequestBody @Valid DeveloperRequestDTO request) {

        List<LanguageRequestDTO> unique = request
                .getLanguages()
                .stream()
                .peek(x -> {
                    x.setName(x.getName().toUpperCase());
                })
                .distinct()
                .collect(Collectors.toList());

        if (request.getLanguages().size() != unique.size()) {
            throw new IllegalArgumentException(
                    "You provided two same languages. Elements of the list must be unique ignore case");
        }

        request.setLanguages(unique);
        return service.create(request);
    }

    @GetMapping("/api")
    public List<DeveloperResponseDTO> getAll() {
        return service.getAll();
    }
}
