package com.grpc.user.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.grpc.user.service.impl.UserServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@Configuration
public class UserGrpcServerConfig {
	
	@Value("${user.server.port}")
	private String grpcUserServerPort;
	
//	private Server server;

    @Bean
    public Server createServer() {
        int port = Integer.valueOf(grpcUserServerPort);
        Server server = ServerBuilder.forPort(port)
                .addService(userServiceImpl())
                .build();
        return server;
    }
	
//	@PostConstruct
//	public void startServer() {
//		try {
//			createServer().start();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Bean
	public UserServiceImpl userServiceImpl() {
		return new UserServiceImpl();
	}

}
