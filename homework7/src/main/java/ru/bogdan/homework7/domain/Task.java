package ru.bogdan.homework7.domain;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false)
    private Long id;
    @Column (name = "description", nullable = false,length = 500)
    private String description;
    @Column (name = "status", nullable = false)
    @Enumerated
    private TaskStatus status;
    @Column (name = "create_date", nullable = false)
    private LocalDate createDate;
}
