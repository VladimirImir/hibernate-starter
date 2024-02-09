package com.dev.entity;

import com.dev.converter.BirthdateConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    private String username;
    private String firstname;
    private String lastname;

    //@Convert(converter = BirthdateConverter.class)
    @Column(name = "birth_date")
    private Birthdate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

}
