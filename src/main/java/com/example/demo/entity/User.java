package com.example.demo.entity;

public class User {
	
	private Integer id;
	private String username;
	private String password;
	private String disk_name;
	private String disk_password;
	
	/**
	 * 未提供云盘的用户信息创建
	 * @param id
	 * @param username
	 * @param password
	 */
	public User(Integer id, String username, String password) {
		this.id = id;
		this.setUsername(username);
		this.password = password;
	}
	
	/**
	 * 提供云盘的用户信息创建
	 * @param id
	 * @param username
	 * @param password
	 * @param disk_name
	 * @param disk_password
	 */
	public User(Integer id, String username, String password, String disk_name, String disk_password) {
		this.id = id;
		this.setUsername(username);
		this.password = password;
		this.disk_name = disk_name;
		this.disk_password = disk_password;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDisk_name() {
		return disk_name;
	}
	
	public void setDisk_name(String disk_name) {
		this.disk_name = disk_name;
	}
	
	public String getDisk_password() {
		return disk_password;
	}
	
	public void setDisk_password(String disk_password) {
		this.disk_password = disk_password;
	}
}
