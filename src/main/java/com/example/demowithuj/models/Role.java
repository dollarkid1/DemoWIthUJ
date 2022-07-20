package com.example.demowithuj.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @NoArgsConstructor @Setter @Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }
}
