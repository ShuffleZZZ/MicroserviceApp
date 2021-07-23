package com.shufflezzz.ApiGateway.controller;

import com.shufflezzz.Connector.config.RabbitMQ;
import com.shufflezzz.Connector.models.dto.DataSetDto;
import com.shufflezzz.Connector.models.dto.UpdateDataSetDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/datasets")
public class DataSetController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/getData/{id}")
    public Object getData(@PathVariable(value = "id") Long dataSetId) {
        return rabbitTemplate.convertSendAndReceive(RabbitMQ.EXCHANGE, RabbitMQ.DATA_GET_QUEUE, dataSetId);
    }

    @GetMapping("/getAllDataSets")
    public List<DataSetDto> getALlDataSets() {
        return (List<DataSetDto>) rabbitTemplate.convertSendAndReceive(
                RabbitMQ.EXCHANGE,
                RabbitMQ.DATA_SET_GET_ALL_QUEUE,
                new Object()
        );
    }

    @GetMapping("/{id}")
    public DataSetDto getDataSetById(@PathVariable(value = "id") Long dataSetId) {
        return (DataSetDto) rabbitTemplate.convertSendAndReceive(
                RabbitMQ.EXCHANGE,
                RabbitMQ.DATA_SET_GET_QUEUE,
                dataSetId
        );
    }

    @PostMapping("/")
    public DataSetDto createDataSet(@RequestBody DataSetDto dataSet) {
        return (DataSetDto) rabbitTemplate.convertSendAndReceive(
                RabbitMQ.EXCHANGE,
                RabbitMQ.DATA_SET_CREATE_QUEUE,
                dataSet
        );
    }

    @PutMapping("/{id}")
    public DataSetDto updateDataSet(@PathVariable(value = "id") Long dataSetId, @RequestBody DataSetDto newDataSet) {
        return (DataSetDto) rabbitTemplate.convertSendAndReceive(
                RabbitMQ.EXCHANGE,
                RabbitMQ.DATA_SET_UPDATE_QUEUE,
                new UpdateDataSetDto(dataSetId, newDataSet)
        );
    }

    @DeleteMapping("/{id}")
    public DataSetDto deleteDataSet(@PathVariable(value = "id") Long dataSetId) {
        return (DataSetDto) rabbitTemplate.convertSendAndReceive(
                RabbitMQ.EXCHANGE,
                RabbitMQ.DATA_SET_DELETE_QUEUE,
                dataSetId
        );
    }
}
