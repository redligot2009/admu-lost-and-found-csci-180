package org.admu.lostandfound.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="username", unique = true, nullable = false)
    private String username;

    @NotEmpty
    @Column(name="email", unique = true)
    private String email;

    @NotEmpty
    @Column(name="password")
    private String password;

    @NotEmpty
    @Column(name="role")
    private String role;

    @OneToMany(mappedBy = "claimer")
    private Set<Claim> claims;
}
