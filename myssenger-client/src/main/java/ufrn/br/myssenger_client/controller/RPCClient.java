package ufrn.br.myssenger_client.controller;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RPCClient implements AutoCloseable {

    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";
    
    private static final String RABBITMQ_SERVER = "ec2-3-15-213-254.us-east-2.compute.amazonaws.com";
    private static final String RABBITMQ_USER = "gilneyjr";
    private static final String RABBITMQ_PASS = "1234";

    public RPCClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_SERVER);
    	factory.setUsername(RABBITMQ_USER);
    	factory.setPassword(RABBITMQ_PASS);

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public byte[] call(byte[] message) throws IOException, InterruptedException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        // Using Default Exchange and requestQueueName queue
        channel.basicPublish("", requestQueueName, props, message);

        final BlockingQueue<byte[]> response = new ArrayBlockingQueue<>(1);

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response.offer(delivery.getBody());
            }
        }, consumerTag -> {
        });

        byte[] result = response.take();
        channel.basicCancel(ctag);
        return result;
    }

    public void close() throws IOException {
        connection.close();
    }
}