### There are three microservices<br/>

1. ***grpc-user-service*** is a gRPC microservice that stores customer data in its own domain database.<br/>
 a. Data is stored in an In Memory H2 Database. Data is fetched by service using Spring Data JPA.<br/>
 b. The service exposes a gRPC endpoint/method to return the customer information based on username.<br/>
 c. It is also integrated with Kafka and acts as a Producer and drops the customer object as a string on the Kafka Topic.<br/>
 
2. ***grpc-order-service*** is a simple gRPC service that returns an order for a specific user. It communicates with the ***grpc-user-service*** to fetch the customer id based on the customer name.<br/>
 a. Data is stored in an In Memory H2 Database. Data is fetched by service using Spring Data JPA.<br/>
 b. The service exposes a gRPC endpoint/method to return the order information based on username.<br/>
 
3. ***spring-grpc-kafka-client*** is a gRPC service and a Kafka Consumer. The service consumes a message (username) from a Topic (assumed to be received from an event). It communicates with ***grpc-user-service***.<br/>
 a. Integrated with Kafka and acts as a Consumer and polls the customer username as a string from the Kafka Topic.<br/>
 
 ### Yet to do<br/>
 1. Explore the other modes of communication other than the so far Unary/Simple RPC:<br/>
  a. Client Streaming RPC<br/>
  b. Server Streaming RPC<br/>
  c. Bidirectional Streaming RPC<br/>
