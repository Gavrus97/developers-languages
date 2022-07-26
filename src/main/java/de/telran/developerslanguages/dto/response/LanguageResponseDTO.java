package de.telran.developerslanguages.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LanguageResponseDTO {

    private Long id;
    private String name;
    private Integer usages;
}
