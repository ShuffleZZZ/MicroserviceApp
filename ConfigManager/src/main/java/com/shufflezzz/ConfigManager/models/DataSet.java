package com.shufflezzz.ConfigManager.models;

import com.shufflezzz.Connector.models.Role;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "data_sets")
public class DataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "dset_name")
    private String setName;

    @ManyToOne
    @JoinColumn(name = "dsrc_connection_name")
    @Cascade(value = org.hibernate.annotations.CascadeType.DETACH)
    private DataSource connectionName;

    @Column(name = "dset_sql_request")
    private String sqlQuery;

    @ElementCollection
    @CollectionTable(name = "dset_list", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "dset_available_roles")
    @Enumerated(EnumType.STRING)
    private List<Role> availableRoles;


    public DataSet(String setName, DataSource connectionName, String sqlQuery, List<Role> availableRoles) {
        this.setName = setName;
        this.connectionName = connectionName;
        this.sqlQuery = sqlQuery;
        this.availableRoles = availableRoles;
    }

    public DataSet() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public DataSource getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(DataSource connectionName) {
        this.connectionName = connectionName;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlRequest) {
        this.sqlQuery = sqlRequest;
    }

    public List<Role> getAvailableRoles() {
        return availableRoles;
    }

    public void setAvailableRoles(List<Role> availableRoles) {
        this.availableRoles = availableRoles;
    }
}
