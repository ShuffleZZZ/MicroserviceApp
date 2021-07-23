package com.shufflezzz.ConfigManager.models;

import com.shufflezzz.Connector.models.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "data_sources", uniqueConstraints = @UniqueConstraint(columnNames = "dsrc_connection_name"))
public class DataSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "dsrc_connection_name")
    private String name;

    @Column(name = "dsrc_address")
    private String address;

    @Column(name = "dsrc_port")
    private int port;

    @Column(name = "dsrc_login")
    private String login;

    @Column(name = "dsrc_password")
    private String password;

    @ElementCollection
    @CollectionTable(name = "dsrc_list", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "dsrc_available_roles")
    @Enumerated(EnumType.STRING)
    private List<Role> availableRoles;


    public DataSource(String name, String address, int port, String login, String password, List<Role> availableRoles) {
        this.name = name;
        this.address = address;
        this.port = port;
        this.login = login;
        this.password = password;
        this.availableRoles = availableRoles;
    }

    public DataSource() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getAvailableRoles() {
        return availableRoles;
    }

    public void setAvailableRoles(List<Role> availableRoles) {
        this.availableRoles = availableRoles;
    }
}
