package com.fhr.readwritedemo.dtomodels;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息
 * 
 * @author fhr
 * @date
 */
@ApiModel
public class RwUserInfo implements java.io.Serializable {
	private static final long serialVersionUID = 5263520779883381805L;
	
	@ApiModelProperty(value = "用户编号", required = false)
	private int userId;
	
	@ApiModelProperty(value = "用户名字", required = true)
	private String name;
	
	@ApiModelProperty(value = "用户年龄", required = true)
	private short age;
	
	@ApiModelProperty(value = "用户性别", required = true)
	private String gender;
	
	@ApiModelProperty(value = "用户邮箱", required = false)
	private String email;

	public RwUserInfo(int userId, String name, short age, String gender, String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
	}

	public RwUserInfo(String name, short age, String gender, String email) {
		this(-1, name, age, gender, email);
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
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
		return String.format("{userId:%d,name:%s,age:%d,gender:%s,email:%s}", userId, name, age, gender, email);
	}
}
