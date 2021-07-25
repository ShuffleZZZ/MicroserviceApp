package com.shufflezzz.ConfigManager.listener;

import com.google.gson.Gson;
import com.shufflezzz.ConfigManager.models.DataSet;
import com.shufflezzz.ConfigManager.service.DataSetService;
import com.shufflezzz.Connector.config.RabbitMQ;
import com.shufflezzz.Connector.models.dto.DataSetDto;
import com.shufflezzz.Connector.models.dto.UpdateDataSetDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class DataSetListener {

    @Autowired
    private DataSetService dataSetService;

    private final Gson gson = new Gson();

    @RabbitListener(queues = RabbitMQ.DATA_SET_GET_QUEUE)
    public String handleGet(Message message) {
        final Long dataSetId = gson.fromJson(new String(message.getBody()), Long.class);
        final Optional<DataSet> dataSet = dataSetService.findById(dataSetId);

        return gson.toJson(dataSet.map(set -> dataSetService.dataSetToDto(set)).orElse(null));
    }

    @RabbitListener(queues = RabbitMQ.DATA_SET_GET_ALL_QUEUE)
    public String handleGetAll() {
        return gson.toJson(dataSetService
                .findAll()
                .stream()
                .map(set -> dataSetService.dataSetToDto(set))
                .collect(Collectors.toList()));
    }

    @RabbitListener(queues = RabbitMQ.DATA_SET_CREATE_QUEUE)
    public String handleCreate(Message message) {
        final DataSetDto dataSetDto = gson.fromJson(new String(message.getBody()), DataSetDto.class);
        return gson.toJson(dataSetService.dataSetToDto(dataSetService.save(dataSetService.dtoToDataSet(dataSetDto))));
    }

    @RabbitListener(queues = RabbitMQ.DATA_SET_UPDATE_QUEUE)
    public String handleUpdate(Message message) {
        final UpdateDataSetDto updateDataSetDto = gson.fromJson(new String(message.getBody()), UpdateDataSetDto.class);
        final Optional<DataSet> dataSetEntity = dataSetService.findById(updateDataSetDto.getDataSetId());

        return gson.toJson(dataSetEntity.map(dataSet ->
                dataSetService.dataSetToDto(
                        dataSetService.save(dataSetService.updateDataSet(dataSet, updateDataSetDto.getDataSetDto()))
                )
        ).orElse(null));

    }

    @RabbitListener(queues = RabbitMQ.DATA_SET_DELETE_QUEUE)
    public String handleDelete(Message message) {
        final Long dataSetId = gson.fromJson(new String(message.getBody()), Long.class);
        final Optional<DataSet> dataSetEntity = dataSetService.findById(dataSetId);

        if (dataSetEntity.isPresent()) {
            final DataSet dataSet = dataSetEntity.get();
            dataSetService.delete(dataSet);

            return gson.toJson(dataSetService.dataSetToDto(dataSet));
        }

        return gson.toJson(null);
    }
}
