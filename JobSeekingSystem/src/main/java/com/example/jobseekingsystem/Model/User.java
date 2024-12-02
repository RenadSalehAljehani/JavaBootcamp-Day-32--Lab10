package com.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "system_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can't be empty.")
    @Size(min = 5, message = "Name length must be more than 4 characters.")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain only characters (no numbers).")
    @Column(columnDefinition = "varchar(10) not null")
    @Check(constraints = "length(name) > 4")
    private String name;

    @Email(message = "Invalid email format.")
    @Column(columnDefinition = "varchar(255) unique")
    @Check(constraints = "email LIKE '_%@_%._%'")
    private String email;

    @NotEmpty(message = "Password can't be empty.")
    @Column(columnDefinition = "varchar(10) not null")
    private String password;

    @NotNull(message = "Age can't be empty.")
    @Min(value = 22, message = "Age must be more than 21.")
    @Column(columnDefinition = "int not null")
    @Check(constraints = "age > 21")
    private Integer age;

    @NotEmpty(message = "Role can't be empty.")
    @Pattern(regexp = "^(?i)(JOB_SEEKER|EMPLOYER)$", message = "Role must be either 'JOB_SEEKER' or 'EMPLOYER' only.")
    @Column(columnDefinition = "varchar(10) not null")
    @Check(constraints = "LOWER(role) = 'JOB_SEEKER' OR LOWER(role) = 'EMPLOYER'")
    private String role;
}