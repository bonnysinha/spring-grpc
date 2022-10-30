package com.grpc.order.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.protobuf.util.Timestamps;
import com.grpc.order.service.entity.OrderEntity;
import com.grpc.order.service.repository.OrderRepository;
import com.grpc.stubs.order.Order;
import com.grpc.stubs.order.OrderRequest;
import com.grpc.stubs.order.OrderResponse;
import com.grpc.stubs.order.OrderServiceGrpc;
import com.grpc.stubs.user.UserRequest;
import com.grpc.stubs.user.UserResponse;
import com.grpc.stubs.user.UserServiceGrpc.UserServiceBlockingStub;

import io.grpc.stub.StreamObserver;

public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserServiceBlockingStub userServiceBlockingStub;

	@Override
	public void getOrdersForUser(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
		System.out.println("Request Received" + request.getUsername());
		UserRequest userRequest = UserRequest.newBuilder().setUsername(request.getUsername()).build();
		UserResponse userResponse = userServiceBlockingStub.getUserDetails(userRequest);
		System.out.println("User fetched for username: "+userResponse.getName());
		List<OrderEntity> orderEntLst = orderRepository.findByUserId(userResponse.getId());
		List<Order> orderLst = new ArrayList<>();
		orderLst = Arrays.asList(Order.newBuilder().setOrderId(-1).setUserId(-1).setQuantity(-1).setTotalAmount(0.0)
				.setOrderDate(Timestamps.fromMillis(new Date().getTime())).build());
		if (!orderEntLst.isEmpty()) {
			orderLst = orderEntLst.stream()
					.map(orderEnt -> Order.newBuilder().setOrderId(orderEnt.getOrderId())
							.setUserId(orderEnt.getUserId()).setQuantity(orderEnt.getQuantity())
							.setTotalAmount(orderEnt.getTotalAmount())
							.setOrderDate(Timestamps.fromMillis(orderEnt.getOrderDate().getTime())).build())
					.collect(Collectors.toList());
		}

		OrderResponse orderResponse = OrderResponse.newBuilder().addAllOrder(orderLst).build();

		responseObserver.onNext(orderResponse);
		responseObserver.onCompleted();
	}
}
