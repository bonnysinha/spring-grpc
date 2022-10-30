package com.grpc.order.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.grpc.order.service.config.GrpcServerOps;

@SpringBootApplication
public class GrpcOrderServiceApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(GrpcOrderServiceApplication.class, args);
		GrpcServerOps ops = ctx.getBean(GrpcServerOps.class);
		ops.startServer();
		ops.blockUntilShutdown();
	}

}
