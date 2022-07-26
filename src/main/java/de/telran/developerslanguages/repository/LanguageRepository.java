package de.telran.developerslanguages.repository;

import de.telran.developerslanguages.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    List<Language> findAllByNameIsIn(List<String> names);
}
