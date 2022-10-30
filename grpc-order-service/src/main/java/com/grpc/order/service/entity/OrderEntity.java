package com.grpc.order.service.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ORDERS")
public class OrderEntity {
	@Id
	private int id;
	@Column
	private int orderId;
	@Column
	private int userId;
	@Column
	private int quantity;
	@Column
	private Double totalAmount;
	@Column
	private Date orderDate;
	public OrderEntity(int id, int orderId, int userId, int quantity, Double totalAmount, Date orderDate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.userId = userId;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.orderDate = orderDate;
	}
	public OrderEntity() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}	
}
