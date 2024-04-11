package ru.bogdan.homework4.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "custom-sql")
@Data
public class SQLCustomProperty {
    private String findAll;
    private String saveCat;
    private String deleteCatById;
    private String findCatByName;
    private String updateCat;
}
