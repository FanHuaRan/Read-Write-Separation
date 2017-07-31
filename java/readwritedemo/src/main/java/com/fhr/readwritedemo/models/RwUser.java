package com.fhr.readwritedemo.models;
/**
 * 测试用户表
 * @author fhr
 * @date 2017/07/29
 */
public class RwUser implements java.io.Serializable {
	private static final long serialVersionUID = -5612769375299669730L;
	
	// 编号
	private int userId;
	// 姓名
	private String name;
	// 年龄
	private short age;
	// 性别
	private short gender;
	// 邮箱
	private String email;

	public RwUser(int userId, String name, short age, short gender, String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
	}

	public RwUser(String name, short age, short gender, String email) {
		this(-1, name, age, gender, email);
	}
	
	public RwUser(){
		
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

	public short getGender() {
		return gender;
	}

	public void setGender(short gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("{userId:%d,name:%s,age:%d,gender:%d,email:%s}", userId,name,age,gender,email);
	}

}