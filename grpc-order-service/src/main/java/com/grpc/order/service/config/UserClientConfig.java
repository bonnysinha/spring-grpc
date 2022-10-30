package com.grpc.order.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.grpc.stubs.user.UserServiceGrpc;
import com.grpc.stubs.user.UserServiceGrpc.UserServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Configuration
public class UserClientConfig {
	
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
	
//	public User getUserByUserName(String userName) {
//		UserRequest userRequest = UserRequest.newBuilder()
//				.setUsername(userName)
//				.build();
//		UserResponse userResponse = userServiceBlockingStub.getUserDetails(userRequest);
//	}
}
