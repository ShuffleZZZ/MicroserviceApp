package com.shufflezzz.Connector.models.dto;

public class UpdateDataSourceDto {

    private Long dataSourceId;
    private DataSourceDto dataSourceDto;

    public UpdateDataSourceDto(Long dataSourceId, DataSourceDto dataSourceDto) {
        this.dataSourceId = dataSourceId;
        this.dataSourceDto = dataSourceDto;
    }

    public UpdateDataSourceDto() {
    }

    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public DataSourceDto getDataSourceDto() {
        return dataSourceDto;
    }

    public void setDataSourceDto(DataSourceDto dataSourceDto) {
        this.dataSourceDto = dataSourceDto;
    }
}
