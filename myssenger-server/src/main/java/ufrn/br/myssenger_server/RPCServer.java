package ufrn.br.myssenger_server;

import org.json.JSONException;
import org.json.JSONObject;

import com.rabbitmq.client.*;

import ufrn.br.myssenger_server.service.Service;

public class RPCServer {

    private static final String RPC_QUEUE_NAME = "rpc_queue";
    
    private static final String RABBITMQ_SERVER = "ec2-3-15-213-254.us-east-2.compute.amazonaws.com";
    private static final String RABBITMQ_USER = "gilneyjr";
    private static final String RABBITMQ_PASS = "1234";
    private static final Handle handle = new Handle(new Service());

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_SERVER);
    	factory.setUsername(RABBITMQ_USER);
    	factory.setPassword(RABBITMQ_PASS);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
            channel.queuePurge(RPC_QUEUE_NAME);

            channel.basicQos(1);

            System.out.println(" [x] Awaiting RPC requests");

            Object monitor = new Object();
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();

                JSONObject response = new JSONObject();
                try {
                    handle.handle(new JSONObject(new String(delivery.getBody(), "UTF-8")));
                    response.put("type", "response");
                    response.put("status", "ok");
                } catch (JSONException e) {
                	System.out.println(" [.] " + e.toString());
                    response.put("type", "response");
                    response.put("status", "error");
                    response.put("msg", "JSON format is incompatible with server protocol");
                } catch (Exception e) {
                	System.out.println(" [.] " + e.toString());
                	response.put("type", "response");
                    response.put("status", "error");
                    response.put("msg", e.getMessage());
                } finally {
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.toString().getBytes("UTF-8"));
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    // RabbitMq consumer worker thread notifies the RPC server owner thread
                    synchronized (monitor) {
                        monitor.notify();
                    }
                }
            };

            channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, (consumerTag -> { }));
            // Wait and be prepared to consume the message from RPC client.
            while (true) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}