package de.telran.developerslanguages.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeveloperRequestDTO {

    @NotBlank(message = "Developer name cannot be blank")
    @NotNull(message = "Developer name cannot be null")
    @Size(min = 2, max = 30, message = "Developer name must be 2-30 chars")
    private String name;

    @NotEmpty(message = "List of languages cannot be empty")
    private List< @Valid LanguageRequestDTO> languages;
}

