package com.shufflezzz.ApiGateway.controller;

import com.shufflezzz.Connector.config.RabbitMQ;
import com.shufflezzz.Connector.models.dto.DataSourceDto;
import com.shufflezzz.Connector.models.dto.UpdateDataSourceDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/datasources")
public class DataSourcesController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/getAllDataSources")
    public List<DataSourceDto> getALlDataSources() {
        return (List<DataSourceDto>) rabbitTemplate.convertSendAndReceive(
                RabbitMQ.EXCHANGE,
                RabbitMQ.DATA_SOURCE_GET_ALL_QUEUE,
                new Object()
        );
    }

    @GetMapping("/{id}")
    public DataSourceDto getDataSourceById(@PathVariable(value = "id") Long dataSourceId) {
        return (DataSourceDto) rabbitTemplate.convertSendAndReceive(
                RabbitMQ.EXCHANGE,
                RabbitMQ.DATA_SOURCE_GET_QUEUE,
                dataSourceId
        );
    }

    @PostMapping("/")
    public DataSourceDto createDataSource(@RequestBody DataSourceDto dataSource) {
        return (DataSourceDto) rabbitTemplate.convertSendAndReceive(
                RabbitMQ.EXCHANGE,
                RabbitMQ.DATA_SOURCE_CREATE_QUEUE,
                dataSource
        );
    }

    @PutMapping("/{id}")
    public DataSourceDto updateDataSource(@PathVariable(value = "id") Long dataSourceId,
                                          @RequestBody DataSourceDto newDataSource) {
        return (DataSourceDto) rabbitTemplate.convertSendAndReceive(
                RabbitMQ.EXCHANGE,
                RabbitMQ.DATA_SOURCE_UPDATE_QUEUE,
                new UpdateDataSourceDto(dataSourceId, newDataSource)
        );
    }

    @DeleteMapping("/{id}")
    public DataSourceDto deleteDataSource(@PathVariable(value = "id") Long dataSourceId) {
        return (DataSourceDto) rabbitTemplate.convertSendAndReceive(
                RabbitMQ.EXCHANGE,
                RabbitMQ.DATA_SOURCE_DELETE_QUEUE,
                dataSourceId
        );
    }
}
