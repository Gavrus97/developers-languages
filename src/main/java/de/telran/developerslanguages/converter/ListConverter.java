package de.telran.developerslanguages.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter(autoApply = true)
public class ListConverter implements AttributeConverter<List<String>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return objectMapper.writeValueAsString(strings);
    }

    @SneakyThrows
    @Override
    public List<String> convertToEntityAttribute(String s) {
        return objectMapper.readValue(s, new TypeReference<List<String>>() {});
    }
}
