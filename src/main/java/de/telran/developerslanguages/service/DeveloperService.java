package de.telran.developerslanguages.service;

import de.telran.developerslanguages.dto.request.DeveloperRequestDTO;
import de.telran.developerslanguages.dto.response.DeveloperResponseDTO;

import java.util.List;

public interface DeveloperService {

    DeveloperResponseDTO create(DeveloperRequestDTO request);

    List<DeveloperResponseDTO> getAll();
}
