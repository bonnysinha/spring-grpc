package com.grpc.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.grpc.user.service.config.GrpcServerOps;

@SpringBootApplication
public class GrpcUserServiceApplication {
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(GrpcUserServiceApplication.class, args);
		GrpcServerOps ops = ctx.getBean(GrpcServerOps.class);
		ops.startServer();
		ops.blockUntilShutdown();
	}

}
