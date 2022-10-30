package com.grpc.user.service.model;

public class UserModel {
	private int id;
	private String username;
	private String name;
	private int age;
	private String gender;
	public UserModel(int id, String username, String name, int age, String gender) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public UserModel() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
