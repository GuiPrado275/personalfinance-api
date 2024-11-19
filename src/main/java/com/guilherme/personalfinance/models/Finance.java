package com.guilherme.personalfinance.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = Finance.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Finance {

    public static final String TABLE_NAME = "finance";

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //Many finances for one user
    @JoinColumn(name = "user_id", nullable = false, unique = false)
    private User user;

    @Column(name = "Finance", length = 1000, nullable = false)
    @Size(min = 1, max = 1000)
    @NotBlank
    private String finance;

}
