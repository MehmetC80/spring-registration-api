package de.memozone.springregistrationapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="app_user")
@Data
public class AppUser {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    @Column(length = 60)
    private String password;
    private String role;
    private boolean enabled = false;


}
