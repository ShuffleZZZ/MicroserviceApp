package com.shufflezzz.ApiGateway.controller;

import com.shufflezzz.Connector.config.RabbitMQ;
import com.shufflezzz.Connector.models.dto.DataSourceDto;
import com.shufflezzz.Connector.models.dto.UpdateDataSourceDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/datasources")
public class DataSourcesController extends AbstractController {

    @GetMapping("/getAllDataSources")
    public String getALlDataSources() {
        return handleRequest(RabbitMQ.DATA_SOURCE_GET_ALL_QUEUE, new Object());
    }

    @GetMapping("/{id}")
    public String getDataSourceById(@PathVariable(value = "id") Long dataSourceId) {
        return handleRequest(RabbitMQ.DATA_SOURCE_GET_QUEUE, dataSourceId);
    }

    @PostMapping("/")
    public String createDataSource(@RequestBody DataSourceDto dataSource) {
        return handleRequest(RabbitMQ.DATA_SOURCE_CREATE_QUEUE, dataSource);
    }

    @PutMapping("/{id}")
    public String updateDataSource(@PathVariable(value = "id") Long dataSourceId, @RequestBody DataSourceDto newDataSource) {
        return handleRequest(RabbitMQ.DATA_SOURCE_UPDATE_QUEUE, new UpdateDataSourceDto(dataSourceId, newDataSource));
    }

    @DeleteMapping("/{id}")
    public String deleteDataSource(@PathVariable(value = "id") Long dataSourceId) {
        return handleRequest(RabbitMQ.DATA_SOURCE_DELETE_QUEUE, dataSourceId);
    }
}
