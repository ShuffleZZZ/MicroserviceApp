package com.shufflezzz.ConfigManager.service;

import com.shufflezzz.ConfigManager.models.DataSet;
import com.shufflezzz.ConfigManager.repository.DataSetRepository;
import com.shufflezzz.Connector.models.dto.DataSetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataSetService {

    @Autowired
    private DataSourceService dataSourceService;

    private final DataSetRepository dataSetRepository;

    public DataSetService(DataSetRepository dataSetRepository) {
        this.dataSetRepository = dataSetRepository;
    }

    public DataSetDto dataSetToDto(DataSet dataSet) {
        return new DataSetDto(
                dataSet.getSetName(),
                dataSourceService.dataSourceToDto(dataSet.getConnectionName()),
                dataSet.getSqlQuery(),
                dataSet.getAvailableRoles()
        );
    }

    public void delete(DataSet dataSet) {
        dataSetRepository.delete(dataSet);
    }

    public DataSet dtoToDataSet(DataSetDto dataSetDto) {
        return new DataSet(
                dataSetDto.getSetName(),
                dataSourceService.dtoToDataSource(dataSetDto.getConnectionName()),
                dataSetDto.getSqlQuery(),
                dataSetDto.getAvailableRoles()
        );
    }

    public List<DataSet> findAll() {
        return dataSetRepository.findAll();
    }

    public Optional<DataSet> findById(Long id) {
        return dataSetRepository.findById(id);
    }

    public DataSet save(DataSet dataSet) {
        return dataSetRepository.save(dataSet);
    }

    public DataSet updateDataSet(DataSet dataSet, DataSetDto dataSetDto) {
        dataSet.setSetName(dataSetDto.getSetName());
        dataSet.setConnectionName(dataSourceService.dtoToDataSource(dataSetDto.getConnectionName()));
        dataSet.setSqlQuery(dataSetDto.getSqlQuery());
        dataSet.setAvailableRoles(dataSetDto.getAvailableRoles());

        return dataSet;
    }
}
