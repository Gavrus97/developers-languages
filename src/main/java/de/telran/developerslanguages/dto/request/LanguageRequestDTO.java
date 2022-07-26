package de.telran.developerslanguages.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LanguageRequestDTO {

    @NotNull(message = "Language name cannot be null")
    @NotBlank(message = "Language name cannot be blank")
    @Size(min = 2, max = 15, message = "Language name must be 2-15 chars")
    private String name;
}
