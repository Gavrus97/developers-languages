package de.telran.developerslanguages.repository;

import de.telran.developerslanguages.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    boolean existsByName(String name);
}
