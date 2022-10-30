package com.grpc.user.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.grpc.stubs.user.Gender;
import com.grpc.stubs.user.UserRequest;
import com.grpc.stubs.user.UserResponse;
import com.grpc.stubs.user.UserServiceGrpc;
import com.grpc.user.service.entity.UserEntity;
import com.grpc.user.service.model.UserModel;
import com.grpc.user.service.repositories.UserRepository;

//@Component
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageProducer messageProducer;
	
	@Override
	public void getUserDetails(UserRequest request,
	        io.grpc.stub.StreamObserver<UserResponse> responseObserver) {
		List<UserEntity> userEntityList = userRepository.findByUsername(request.getUsername());
		UserEntity userEntity = userEntityList.stream()
				.findFirst()
				.orElse(new UserEntity(0, "dummy", "Dummy User", 0, "MALE"));
		UserModel model = new UserModel();
		messageProducer.publishMessage("Received data of: "+userEntity.toString());
		BeanUtils.copyProperties(userEntity, model);
		
		UserResponse.Builder userResponseBuilder = UserResponse.newBuilder()
				.setId(model.getId())
				.setName(model.getName())
				.setUsername(model.getUsername())
				.setAge(model.getAge())
				.setGender(Gender.valueOf(model.getGender()));
		
//		responseObserver.onCompleted();
		
		UserResponse userResponse = userResponseBuilder.build();
		responseObserver.onNext(userResponse);
		responseObserver.onCompleted();
	}

}
