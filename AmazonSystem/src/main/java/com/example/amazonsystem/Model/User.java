package com.example.amazonsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username cannot be empty")
    @Size(min = 5, message = "username must be more than 5 length long")
    @Column(columnDefinition = "varchar(15) not null unique")
    private String userName;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 6 , message = "Password must be more than 6 length long ")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$", message = "Password must be digits and characters only")
    @Column(columnDefinition = "varchar(12) not null")
    private String password;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "must be a valid email")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;
    @NotEmpty(message = "Role cannot be empty")
    @Pattern(regexp = "Admin|Customer|admin|customer", message = "Role must be either Admin or Customer ")
    @Column(columnDefinition = "varchar(8) check(role = 'Admin' or role ='Customer')")
    private String role;
    @NotNull(message = "balance cannot be empty")
    @Positive(message = "balance must be positive")
    @Column(columnDefinition = "Double not null")
    private Double balance;
}
