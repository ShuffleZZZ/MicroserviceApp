package com.shufflezzz.ConfigManager.service;

import com.shufflezzz.ConfigManager.models.DataSource;
import com.shufflezzz.ConfigManager.repository.DataSourceRepository;
import com.shufflezzz.Connector.models.dto.DataSourceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataSourceService {

    private final DataSourceRepository dataSourceRepository;

    public DataSourceService(DataSourceRepository dataSourceRepository) {
        this.dataSourceRepository = dataSourceRepository;
    }

    public DataSourceDto dataSourceToDto(DataSource dataSource) {
        return new DataSourceDto(
                dataSource.getName(),
                dataSource.getAddress(),
                dataSource.getPort(),
                dataSource.getLogin(),
                dataSource.getPassword(),
                dataSource.getAvailableRoles()
        );
    }

    public void delete(DataSource dataSource) {
        dataSourceRepository.delete(dataSource);
    }

    public DataSource dtoToDataSource(DataSourceDto dataSourceDto) {
        return new DataSource(
                dataSourceDto.getName(),
                dataSourceDto.getAddress(),
                dataSourceDto.getPort(),
                dataSourceDto.getLogin(),
                dataSourceDto.getPassword(),
                dataSourceDto.getAvailableRoles()
        );
    }

    public List<DataSource> findAll() {
        return dataSourceRepository.findAll();
    }

    public Optional<DataSource> findById(Long id) {
        return dataSourceRepository.findById(id);
    }

    public DataSource save(DataSource dataSource) {
        return dataSourceRepository.save(dataSource);
    }

    public DataSource updateDataSource(DataSource dataSource, DataSourceDto dataSourceDto) {
        dataSource.setName(dataSourceDto.getName());
        dataSource.setAddress(dataSourceDto.getAddress());
        dataSource.setPort(dataSourceDto.getPort());
        dataSource.setLogin(dataSourceDto.getLogin());
        dataSource.setPassword(dataSourceDto.getPassword());
        dataSource.setAvailableRoles(dataSourceDto.getAvailableRoles());

        return dataSource;
    }
}
