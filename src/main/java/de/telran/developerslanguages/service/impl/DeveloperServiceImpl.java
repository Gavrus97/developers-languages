package de.telran.developerslanguages.service.impl;

import de.telran.developerslanguages.converter.DeveloperConverter;
import de.telran.developerslanguages.dto.request.DeveloperRequestDTO;
import de.telran.developerslanguages.dto.response.DeveloperResponseDTO;
import de.telran.developerslanguages.entity.Developer;
import de.telran.developerslanguages.entity.Language;
import de.telran.developerslanguages.repository.DeveloperRepository;
import de.telran.developerslanguages.service.DeveloperService;
import de.telran.developerslanguages.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;
    private final LanguageService languageService;

    @Override
    @Transactional
    public DeveloperResponseDTO create(DeveloperRequestDTO request) {

        String name = request.getName();
        if (developerRepository.existsByName(name)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Developer %s already exists", name)
            );
        }

        var languages = languageService
                .saveLanguages(request.getLanguages())
                .stream()
                .map(Language::getName)
                .collect(Collectors.toList());

        var developer = Developer
                .builder()
                .name(name)
                .languages(languages)
                .build();

        developerRepository.save(developer);
        return DeveloperConverter.convertDeveloperIntoResponseDTO(developer);
    }

    @Override
    public List<DeveloperResponseDTO> getAll() {
        var developers = developerRepository.findAll();

        if (developers.isEmpty()) {
            return List.of();
        }

        return developers
                .stream()
                .map(DeveloperConverter::convertDeveloperIntoResponseDTO)
                .collect(Collectors.toList());
    }
}
