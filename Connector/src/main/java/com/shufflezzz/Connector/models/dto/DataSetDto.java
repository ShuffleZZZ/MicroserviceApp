package com.shufflezzz.Connector.models.dto;

import com.shufflezzz.Connector.models.Role;

import java.util.List;

public class DataSetDto {

    private String setName;
    private String connectionName;
    private String sqlQuery;
    private List<Role> availableRoles;

    public DataSetDto(String setName, String connectionName, String sqlQuery, List<Role> availableRoles) {
        this.setName = setName;
        this.connectionName = connectionName;
        this.sqlQuery = sqlQuery;
        this.availableRoles = availableRoles;
    }

    public DataSetDto() {
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public List<Role> getAvailableRoles() {
        return availableRoles;
    }

    public void setAvailableRoles(List<Role> availableRoles) {
        this.availableRoles = availableRoles;
    }
}
