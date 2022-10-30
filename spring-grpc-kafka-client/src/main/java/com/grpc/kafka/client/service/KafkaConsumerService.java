package com.grpc.kafka.client.service;


import com.grpc.stubs.user.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);
    @Autowired
    private GrpcClientService grpcClientService;

    @KafkaListener(topics = "sampleTopic", groupId = "my-group-id")
    public void consume(String message) {
        UserRequest userReq = UserRequest.newBuilder()
                .setUsername(message)
                .build();
        log.info("Received message from kafka: " + message);
        log.info("Created user request object: " + userReq.toString());
        grpcClientService.invokeUserGrpcService(userReq);
    }
}
