package com.shufflezzz.ConfigManager.listener;

import com.google.gson.Gson;
import com.shufflezzz.ConfigManager.models.DataSource;
import com.shufflezzz.ConfigManager.service.DataSourceService;
import com.shufflezzz.Connector.config.RabbitMQ;
import com.shufflezzz.Connector.models.dto.DataSourceDto;
import com.shufflezzz.Connector.models.dto.UpdateDataSourceDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class DataSourceListener {

    @Autowired
    private DataSourceService dataSourceService;

    private final Gson gson = new Gson();

    @RabbitListener(queues = RabbitMQ.DATA_SOURCE_GET_QUEUE)
    public String handleGet(Message message) {
        final Long dataSourceId = gson.fromJson(new String(message.getBody()), Long.class);
        final Optional<DataSource> dataSourceEntity = dataSourceService.findById(dataSourceId);

        return gson.toJson(dataSourceEntity.map(source -> dataSourceService.dataSourceToDto(source)).orElse(null));
    }

    @RabbitListener(queues = RabbitMQ.DATA_SOURCE_GET_ALL_QUEUE)
    public String handleGetAll() {
        return gson.toJson(dataSourceService
            .findAll()
            .stream()
            .map(source -> dataSourceService.dataSourceToDto(source))
            .collect(Collectors.toList()));
    }

    @RabbitListener(queues = RabbitMQ.DATA_SOURCE_CREATE_QUEUE)
    public String handleCreate(Message message) {
        final DataSourceDto dataSourceDto = gson.fromJson(new String(message.getBody()), DataSourceDto.class);

        return gson.toJson(dataSourceService.dataSourceToDto(
            dataSourceService.save(dataSourceService.dtoToDataSource(dataSourceDto))
        ));
    }

    @RabbitListener(queues = RabbitMQ.DATA_SOURCE_UPDATE_QUEUE)
    public String handleUpdate(Message message) {
        final UpdateDataSourceDto updateDataSourceDto =
            gson.fromJson(new String(message.getBody()), UpdateDataSourceDto.class);
        final Optional<DataSource> dataSourceEntity = dataSourceService.findById(updateDataSourceDto.getDataSourceId());

        return gson.toJson(dataSourceEntity.map(dataSource ->
            dataSourceService.dataSourceToDto(
                dataSourceService.save(
                    dataSourceService.updateDataSource(dataSource, updateDataSourceDto.getDataSourceDto())
                )
            )
        ).orElse(null));
    }

    @RabbitListener(queues = RabbitMQ.DATA_SOURCE_DELETE_QUEUE)
    public String handleDelete(Message message) {
        final Long dataSourceId = gson.fromJson(new String(message.getBody()), Long.class);
        final Optional<DataSource> dataSourceEntity = dataSourceService.findById(dataSourceId);

        if (dataSourceEntity.isPresent()) {
            final DataSource dataSource = dataSourceEntity.get();
            dataSourceService.delete(dataSource);

            return gson.toJson(dataSourceService.dataSourceToDto(dataSource));
        }

        return gson.toJson(null);
    }
}
