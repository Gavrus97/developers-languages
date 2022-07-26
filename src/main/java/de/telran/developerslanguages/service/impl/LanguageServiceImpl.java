package de.telran.developerslanguages.service.impl;

import de.telran.developerslanguages.converter.LanguageConverter;
import de.telran.developerslanguages.dto.request.LanguageRequestDTO;
import de.telran.developerslanguages.dto.response.LanguageResponseDTO;
import de.telran.developerslanguages.entity.Language;
import de.telran.developerslanguages.repository.LanguageRepository;
import de.telran.developerslanguages.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repository;

    @Override
    public LanguageResponseDTO getMostPopular() {
        var languages = repository.findAll();

        var popular = languages
                .stream()
                .max(Comparator
                        .comparing(Language::getCountOfUsage))
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "No language found"
                        )
                );
        return LanguageConverter.convertLanguageToResponseDTO(popular);
    }

    @Override
    public List<Language> saveLanguages(List<LanguageRequestDTO> list) {
        List<String> languageNames = list.stream().map(LanguageRequestDTO::getName).collect(Collectors.toList());

        var existing = repository.findAllByNameIsIn(languageNames);

        if (!existing.isEmpty()) {
            languageNames
                    .removeAll(
                            existing
                                    .stream()
                                    .map(Language::getName)
                                    .collect(Collectors.toList())
                    );

            existing.forEach(x -> x.setCountOfUsage(x.getCountOfUsage() + 1));
        }

        var newLanguages = languageNames
                .stream()
                .map(x -> Language
                        .builder()
                        .name(x)
                        .countOfUsage(1)
                        .build())
                .collect(Collectors.toList());

        existing.addAll(newLanguages);

        return repository.saveAll(existing);
    }
}
