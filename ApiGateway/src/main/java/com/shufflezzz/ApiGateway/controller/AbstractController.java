package com.shufflezzz.ApiGateway.controller;

import com.google.gson.Gson;
import com.shufflezzz.Connector.config.RabbitMQ;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractController {
    @Autowired
    protected RabbitTemplate rabbitTemplate;

    protected final Gson gson = new Gson();

    protected String handleRequest(String queue, Object data) {
        byte[] response = (byte[]) rabbitTemplate.convertSendAndReceive(RabbitMQ.EXCHANGE, queue, gson.toJson(data));

        if (response != null) {
            return new String(response);
        }

        return null;
    }
}
