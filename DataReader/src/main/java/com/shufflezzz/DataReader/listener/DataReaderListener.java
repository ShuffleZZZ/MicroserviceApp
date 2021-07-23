package com.shufflezzz.DataReader.listener;

import com.google.gson.Gson;
import com.shufflezzz.Connector.config.RabbitMQ;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DataReaderListener {

    private final Gson gson = new Gson();

    @RabbitListener(queues = RabbitMQ.DATA_GET_QUEUE)
    public Object handleGet(Message message) {
        final Long dataSetId = gson.fromJson(new String(message.getBody()), Long.class);

        // TODO.

        return null;
    }
}
