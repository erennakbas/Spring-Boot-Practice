package com.warmup.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Entity(name="Users")
@Data
public class User extends BaseEntity {
    @Id
    @Column(unique = true, nullable = false)
    @SequenceGenerator(name="UserSequence", initialValue = 100, allocationSize = 5)
    @GeneratedValue(generator = "UserSequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, unique = true, length = 30)
    @NotNull(message = "Please provide an username.")
    @Size(min=10, max=30, message="Please provide a username with the length between 10 and 30 characters.")
    private String username;
    @Column(nullable = false, length = 50)
    @Size(min=2, max=30, message="Please provide a firstName with the length between 2 and 30 characters.")
    @NotNull(message = "Please provide a firstname.")
    private String firstName;
    @Column(nullable = false, length = 50)
    @NotNull(message = "Please provide a lastname.")
    @Size(min=2, max=30, message="Please provide a lastName with the length between 2 and 30 characters.")
    private String lastName;
    @NotNull(message = "Please provide an age.")
    @Min(value=18, message="Please provide an age that is between 18 and 120")
    @Max(value=120, message="Please provide an age that is between 18 and 120")
    private int age;
    @Column(nullable = false, length = 11)
    @NotNull(message = "Please provide an nationalID.")
    @Size(min=11, max=11, message="Please provide a nationalID with the length of 11.")
    private String nationalID;
    @Column(nullable = false, length = 20)
    @NotNull(message = "Please provide a password")
    @Size(min=8, max=20, message="Please provide a nationalID with the length between 8 and 20 characters.")
    private String password;
}
