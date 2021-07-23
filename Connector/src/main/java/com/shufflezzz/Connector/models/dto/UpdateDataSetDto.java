package com.shufflezzz.Connector.models.dto;

public class UpdateDataSetDto {

    private Long dataSetId;
    private DataSetDto dataSetDto;

    public UpdateDataSetDto(Long dataSetId, DataSetDto dataSetDto) {
        this.dataSetId = dataSetId;
        this.dataSetDto = dataSetDto;
    }

    public UpdateDataSetDto() {
    }

    public Long getDataSetId() {
        return dataSetId;
    }

    public void setDataSetId(Long dataSetId) {
        this.dataSetId = dataSetId;
    }

    public DataSetDto getDataSetDto() {
        return dataSetDto;
    }

    public void setDataSetDto(DataSetDto dataSetDto) {
        this.dataSetDto = dataSetDto;
    }
}
