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
public class DataSetController extends AbstractController {

    @GetMapping("/getData/{id}")
    public String getData(@PathVariable(value = "id") Long dataSetId) {
        return handleRequest(RabbitMQ.DATA_GET_QUEUE, dataSetId);
    }

    @GetMapping("/getAllDataSets")
    public String getALlDataSets() {
        return handleRequest(RabbitMQ.DATA_SET_GET_ALL_QUEUE, new Object());
    }

    @GetMapping("/{id}")
    public String getDataSetById(@PathVariable(value = "id") Long dataSetId) {
        return handleRequest(RabbitMQ.DATA_SET_GET_QUEUE, dataSetId);
    }

    @PostMapping("/")
    public String createDataSet(@RequestBody DataSetDto dataSet) {
        return handleRequest(RabbitMQ.DATA_SET_CREATE_QUEUE, dataSet);
    }

    @PutMapping("/{id}")
    public String updateDataSet(@PathVariable(value = "id") Long dataSetId, @RequestBody DataSetDto newDataSet) {
        return handleRequest(RabbitMQ.DATA_SET_UPDATE_QUEUE, new UpdateDataSetDto(dataSetId, newDataSet));
    }

    @DeleteMapping("/{id}")
    public String deleteDataSet(@PathVariable(value = "id") Long dataSetId) {
        return handleRequest(RabbitMQ.DATA_SET_DELETE_QUEUE, dataSetId);
    }
}
