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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DataSetListener {

    @Autowired
    private DataSetService dataSetService;

    private final Gson gson = new Gson();

    @RabbitListener(queues = RabbitMQ.DATA_SET_GET_QUEUE)
    public DataSetDto handleGet(Message message) {
        final Long dataSetId = gson.fromJson(new String(message.getBody()), Long.class);
        final Optional<DataSet> dataSet = dataSetService.findById(dataSetId);

        return dataSet.map(set -> dataSetService.dataSetToDto(set)).orElse(null);
    }

    @RabbitListener(queues = RabbitMQ.DATA_SET_GET_ALL_QUEUE)
    public List<DataSetDto> handleGetAll() {
        return dataSetService
                .findAll()
                .stream()
                .map(set -> dataSetService.dataSetToDto(set))
                .collect(Collectors.toList());
    }

    @RabbitListener(queues = RabbitMQ.DATA_SET_CREATE_QUEUE)
    public DataSetDto handleCreate(Message message) {
        final DataSetDto dataSetDto = gson.fromJson(new String(message.getBody()), DataSetDto.class);
        return dataSetService.dataSetToDto(dataSetService.save(dataSetService.dtoToDataSet(dataSetDto)));
    }

    @RabbitListener(queues = RabbitMQ.DATA_SET_UPDATE_QUEUE)
    public DataSetDto handleUpdate(Message message) {
        final UpdateDataSetDto updateDataSetDto = gson.fromJson(new String(message.getBody()), UpdateDataSetDto.class);
        final Optional<DataSet> dataSetEntity = dataSetService.findById(updateDataSetDto.getDataSetId());

        return dataSetEntity.map(dataSet ->
                dataSetService.dataSetToDto(
                        dataSetService.save(dataSetService.updateDataSet(dataSet, updateDataSetDto.getDataSetDto()))
                )
        ).orElse(null);

    }

    @RabbitListener(queues = RabbitMQ.DATA_SET_DELETE_QUEUE)
    public DataSetDto handleDelete(Message message) {
        final Long dataSetId = gson.fromJson(new String(message.getBody()), Long.class);
        final Optional<DataSet> dataSetEntity = dataSetService.findById(dataSetId);

        if (dataSetEntity.isPresent()) {
            final DataSet dataSet = dataSetEntity.get();
            dataSetService.delete(dataSet);

            return dataSetService.dataSetToDto(dataSet);
        }

        return null;
    }
}
