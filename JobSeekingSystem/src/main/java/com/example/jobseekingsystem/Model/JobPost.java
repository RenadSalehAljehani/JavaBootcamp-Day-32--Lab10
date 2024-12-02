package com.example.jobseekingsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Title can't be empty.")
    @Size(min = 5, message = "Title length must be more than 4 characters.")
    @Column(columnDefinition = "varchar(30) not null")
    @Check(constraints = "length(title) > 4")
    private String title;

    @NotEmpty(message = "Description can't be empty.")
    @Column(columnDefinition = "varchar(500) not null")
    private String description;

    @NotEmpty(message = "Location can't be empty.")
    @Column(columnDefinition = "varchar(200) not null")
    private String location;

    @NotNull(message = "Salary can't be empty.")
    @Column(columnDefinition = "double not null")
    @Positive(message = "Salary must be a positive number.")
    @Check(constraints = "salary > 0")
    private Double salary;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime postingDate;
}