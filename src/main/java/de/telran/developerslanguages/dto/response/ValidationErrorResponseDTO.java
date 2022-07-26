package de.telran.developerslanguages.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ValidationErrorResponseDTO {

    private HttpStatus status;
    private Map<String, List<String>> errors;
}
