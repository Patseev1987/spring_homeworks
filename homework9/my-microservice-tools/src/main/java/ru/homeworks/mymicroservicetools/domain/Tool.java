package ru.homeworks.mymicroservicetools.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tools_table")
public class Tool {
    @Id
    private String code;
    private String name;
}