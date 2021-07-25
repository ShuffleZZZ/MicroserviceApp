package com.shufflezzz.SecurityManager.models;

import com.shufflezzz.Connector.models.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "app_user", uniqueConstraints = @UniqueConstraint(columnNames = "u_login"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "u_name")
    private String name;

    @Column(name = "u_login")
    private String login;

    @Column(name = "u_password")
    private String password;

    @Column(name = "u_role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.GUEST;

    public User(String name, String login, String password, Role role) {
        this.name = name;
        this.login = login;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.role = role;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String username) {
        this.login = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
