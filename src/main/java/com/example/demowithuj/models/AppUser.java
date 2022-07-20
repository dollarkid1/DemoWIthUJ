package com.example.demowithuj.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String dob;

    private String email;
    private String phoneNumber;
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany
    private Set<Role> roles;

    @CreationTimestamp
    private LocalDateTime dateCreated;





    public AppUser(String phoneNumber, String email, String password) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AppUser appUser = (AppUser) o;
        return id != null && Objects.equals(id, appUser.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
