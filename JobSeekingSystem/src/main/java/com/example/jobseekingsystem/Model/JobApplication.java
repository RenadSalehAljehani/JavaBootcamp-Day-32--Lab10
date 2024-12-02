package com.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User id can't be empty.")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "User id can't be empty.")
    @Column(columnDefinition = "int not null")
    private Integer jobPostId;
}