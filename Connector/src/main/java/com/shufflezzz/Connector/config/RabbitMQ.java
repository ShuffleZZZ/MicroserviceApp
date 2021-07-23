package com.shufflezzz.Connector.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQ {

    public final static String DATA_GET_QUEUE = "data-get-queue";

    public final static String DATA_SET_CREATE_QUEUE = "data-set-create-queue";
    public final static String DATA_SET_DELETE_QUEUE = "data-set-delete-queue";
    public final static String DATA_SET_GET_ALL_QUEUE = "data-set-get-all-queue";
    public final static String DATA_SET_GET_QUEUE = "data-set-get-queue";
    public final static String DATA_SET_UPDATE_QUEUE = "data-set-update-queue";

    public final static String DATA_SOURCE_CREATE_QUEUE = "data-source-create-queue";
    public final static String DATA_SOURCE_DELETE_QUEUE = "data-source-delete-queue";
    public final static String DATA_SOURCE_GET_ALL_QUEUE = "data-source-get-all-queue";
    public final static String DATA_SOURCE_GET_QUEUE = "data-source-get-queue";
    public final static String DATA_SOURCE_UPDATE_QUEUE = "data-source-update-queue";

    public final static String USER_CREATE_QUEUE = "user-create-queue";
    public final static String USER_DELETE_QUEUE = "user-delete-queue";
    public final static String USER_GET_QUEUE = "user-get-queue";
    public final static String USER_UPDATE_QUEUE = "user-update-queue";
    public final static String USER_LOGIN_QUEUE = "user-login-queue";

    public final static String EXCHANGE = "exchange";


    @Bean
    Queue dataGetQueue() {
        return new Queue(DATA_GET_QUEUE, false);
    }


    @Bean
    Queue dataSourceCreateQueue() {
        return new Queue(DATA_SOURCE_CREATE_QUEUE, false);
    }

    @Bean
    Queue dataSourceDeleteQueue() {
        return new Queue(DATA_SOURCE_DELETE_QUEUE, false);
    }

    @Bean
    Queue dataSourceGetAllQueue() {
        return new Queue(DATA_SOURCE_GET_ALL_QUEUE, false);
    }

    @Bean
    Queue dataSourceGetQueue() {
        return new Queue(DATA_SOURCE_GET_QUEUE, false);
    }

    @Bean
    Queue dataSourceUpdateQueue() {
        return new Queue(DATA_SOURCE_UPDATE_QUEUE, false);
    }


    @Bean
    Queue dataSetCreateQueue() {
        return new Queue(DATA_SET_CREATE_QUEUE, false);
    }

    @Bean
    Queue dataSetDeleteQueue() {
        return new Queue(DATA_SET_DELETE_QUEUE, false);
    }

    @Bean
    Queue dataSetGetAllQueue() {
        return new Queue(DATA_SET_GET_ALL_QUEUE, false);
    }

    @Bean
    Queue dataSetGetQueue() {
        return new Queue(DATA_SET_GET_QUEUE, false);
    }

    @Bean
    Queue dataSetUpdateQueue() {
        return new Queue(DATA_SET_UPDATE_QUEUE, false);
    }


    @Bean
    Queue userCreateQueue() {
        return new Queue(USER_CREATE_QUEUE, false);
    }

    @Bean
    Queue userDeleteQueue() {
        return new Queue(USER_DELETE_QUEUE, false);
    }

    @Bean
    Queue userGetQueue() {
        return new Queue(USER_GET_QUEUE, false);
    }

    @Bean
    Queue userUpdateQueue() {
        return new Queue(USER_UPDATE_QUEUE, false);
    }

    @Bean
    Queue userLoginQueue() {
        return new Queue(USER_LOGIN_QUEUE, false);
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }


    @Bean
    Binding dataGetBinding(@Qualifier("dataGetQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DATA_GET_QUEUE);
    }


    @Bean
    Binding dataSourceCreateBinding(@Qualifier("dataSourceCreateQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DATA_SOURCE_CREATE_QUEUE);
    }

    @Bean
    Binding dataSourceDeleteBinding(@Qualifier("dataSourceDeleteQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DATA_SOURCE_DELETE_QUEUE);
    }

    @Bean
    Binding dataSourceGetAllBinding(@Qualifier("dataSourceGetAllQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DATA_SOURCE_GET_ALL_QUEUE);
    }

    @Bean
    Binding dataSourceGetBinding(@Qualifier("dataSourceGetQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DATA_SOURCE_GET_QUEUE);
    }

    @Bean
    Binding dataSourceUpdateBinding(@Qualifier("dataSourceUpdateQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DATA_SOURCE_UPDATE_QUEUE);
    }


    @Bean
    Binding dataSetCreateBinding(@Qualifier("dataSetCreateQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DATA_SET_CREATE_QUEUE);
    }

    @Bean
    Binding dataSetDeleteBinding(@Qualifier("dataSetDeleteQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DATA_SET_DELETE_QUEUE);
    }

    @Bean
    Binding dataSetGetAllBinding(@Qualifier("dataSetGetAllQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DATA_SET_GET_ALL_QUEUE);
    }

    @Bean
    Binding dataSetGetBinding(@Qualifier("dataSetGetQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DATA_SET_GET_QUEUE);
    }

    @Bean
    Binding dataSetUpdateBinding(@Qualifier("dataSetUpdateQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DATA_SET_UPDATE_QUEUE);
    }


    @Bean
    Binding userCreateBinding(@Qualifier("userCreateQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(USER_CREATE_QUEUE);
    }

    @Bean
    Binding userDeleteBinding(@Qualifier("userDeleteQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(USER_DELETE_QUEUE);
    }

    @Bean
    Binding userGetBinding(@Qualifier("userGetQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(USER_GET_QUEUE);
    }

    @Bean
    Binding userUpdateBinding(@Qualifier("userUpdateQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(USER_UPDATE_QUEUE);
    }

    @Bean
    Binding userLoginBinding(@Qualifier("userLoginQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(USER_LOGIN_QUEUE);
    }


    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
