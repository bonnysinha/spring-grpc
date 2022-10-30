package com.grpc.order.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.grpc.order.service.impl.OrderServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@Configuration
public class OrderConfig {
	
	@Value("${order.server.port}")
	private String orderServerPort;
	
	@Bean
    public Server createServer() {
        int port = Integer.valueOf(orderServerPort);
        Server server = ServerBuilder.forPort(port)
                .addService(orderServiceImpl())
                .build();
        return server;
    }
	
	@Bean
	public OrderServiceImpl orderServiceImpl() {
		return new OrderServiceImpl();
	}
}
