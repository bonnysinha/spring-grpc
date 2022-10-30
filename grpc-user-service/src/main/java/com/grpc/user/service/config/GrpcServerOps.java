package com.grpc.user.service.config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.grpc.Server;

@Component
public class GrpcServerOps {
	@Autowired
	private Server server;
	
	public void startServer() {
		try {
			server.start();
			System.out.println("GRPC server started: "+server.getPort());
			Runtime.getRuntime().addShutdownHook(new Thread(()->{
				stopServer();
				System.out.println("JVM shut down manually");
			}));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopServer() {
		try {
			if(null!=server) {
				server.shutdown().awaitTermination(30,TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void blockUntilShutdown() {
		try {
			if(null!=server) {
				server.awaitTermination();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
