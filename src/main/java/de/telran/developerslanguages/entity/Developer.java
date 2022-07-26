package de.telran.developerslanguages.entity;

import de.telran.developerslanguages.converter.ListConverter;
import lombok.*;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Convert(converter = ListConverter.class)
    private List<String> languages;
}
