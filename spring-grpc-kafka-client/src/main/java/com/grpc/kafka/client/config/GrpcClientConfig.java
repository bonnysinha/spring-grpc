package com.grpc.kafka.client.config;

import com.grpc.stubs.user.UserServiceGrpc;
import com.grpc.stubs.user.UserServiceGrpc.UserServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {
    @Bean
    public UserServiceBlockingStub createStub() {
        UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub =
                UserServiceGrpc.newBlockingStub(fetchBlockingChannel());
        return userServiceBlockingStub;
    }

    @Bean
    public ManagedChannel fetchBlockingChannel() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8081")
                .usePlaintext()
                .build();
        return channel;
    }

}
