package com.shufflezzz.Connector.models.dto;

import com.shufflezzz.Connector.models.Role;

import java.util.List;

public class DataSourceDto {

    private String name;
    private String address;
    private int port;
    private String login;
    private String password;
    private List<Role> availableRoles;

    public DataSourceDto(String name, String address, int port, String login, String password, List<Role> availableRoles) {
        this.name = name;
        this.address = address;
        this.port = port;
        this.login = login;
        this.password = password;
        this.availableRoles = availableRoles;
    }

    public DataSourceDto() {
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
