package com.fhr.readwritedemo.dtomodels;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息
 * @author fhr
 * @since 2017/07/29
 */
@ApiModel(value="用户信息",description="关于用户信息的对象")
public class RwUserInfo implements java.io.Serializable {
	private static final long serialVersionUID = 5263520779883381805L;
	
	@ApiModelProperty(value = "编号", required = false)
	private long userId;
	
	@ApiModelProperty(value = "名字", required = true)
	private String name;
	
	@ApiModelProperty(value = "年龄", required = true)
	private int age;
	
	@ApiModelProperty(value = "性别", required = true,allowableValues = "男,女,未知")
	private String gender;
	
	@ApiModelProperty(value = "邮箱", required = false)
	private String email;

	public RwUserInfo(long userId, String name, int age, String gender, String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
	}

	public RwUserInfo(String name, int age, String gender, String email) {
		this(-1, name, age, gender, email);
	}
	
	public RwUserInfo(){
		
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
