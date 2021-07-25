package com.shufflezzz.DataReader.listener;

import com.google.gson.Gson;
import com.shufflezzz.Connector.config.RabbitMQ;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class DataReaderListener {

    private final Gson gson = new Gson();

    @RabbitListener(queues = RabbitMQ.DATA_GET_QUEUE)
    public Object handleGet(Message message) {
        final Long dataSetId = gson.fromJson(new String(message.getBody()), Long.class);

        final String url = String.format("jdbc:postgresql://%s:%s/%s", adress, port, name);

//        Class.forName("com.postgresql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, login, password);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sqlQuery);
//        resultSet.next();

        connection.close();
        return null;
    }
}
