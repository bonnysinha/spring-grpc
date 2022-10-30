package com.grpc.kafka.client.service;

import com.grpc.stubs.user.UserRequest;
import com.grpc.stubs.user.UserResponse;
import com.grpc.stubs.user.UserServiceGrpc.UserServiceBlockingStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrpcClientService {

    private static final Logger log = LoggerFactory.getLogger(GrpcClientService.class);

    @Autowired
    private UserServiceBlockingStub userServiceBlockingStub;

    @Autowired
    private MessageProducer messageProducer;

    public void invokeUserGrpcService(UserRequest request) {
        UserResponse response = userServiceBlockingStub.getUserDetails(request);
        log.info("Fetched user response from server: " + response.toString());
        messageProducer.publishMessage(response.toString());
    }
}
