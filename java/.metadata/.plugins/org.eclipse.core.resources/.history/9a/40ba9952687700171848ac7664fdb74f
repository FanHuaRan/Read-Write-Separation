package com.fhr.readwritedemo.dtoconverters;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.fhr.readwritedemo.dtomodels.RwUserInfo;
import com.fhr.readwritedemo.models.RwUser;
/**
 * rwuser dto和model转换器
 * @author fhr
 * @date 2017/07/29
 */
@Component
public class RwUserInfoConverter {

	public RwUserInfo convert(RwUser rwUser) {
		if (rwUser == null) {
			return null;
		}
		return new RwUserInfo(rwUser.getUserId(),rwUser.getName(), rwUser.getAge(), getGender(rwUser.getGender()), rwUser.getEmail());
	}

	public RwUser convert(RwUserInfo rwUserInfo){
		if(rwUserInfo==null){
			return null;
		}
		return new RwUser(rwUserInfo.getUserId(),rwUserInfo.getName(), rwUserInfo.getAge(), getGender(rwUserInfo.getGender()),rwUserInfo.getEmail());
	}

	public List<RwUserInfo> convert(List<RwUser> rwUsers) {
		if (rwUsers == null) {
			return null;
		}
		List<RwUserInfo> rwUserInfos = new ArrayList<>();
		for (RwUser rwUser : rwUsers) {
			rwUserInfos.add(convert(rwUser));
		}
		return rwUserInfos;
	}

	public List<RwUser> converts(List<RwUserInfo> rwUserInfos) {	
		if (rwUserInfos == null) {
			return null;
		}
		List<RwUser> rwUsers = new ArrayList<>();
		for (RwUserInfo rwUserInfo : rwUserInfos) {
			rwUsers.add(convert(rwUserInfo));
		}
		return rwUsers;
	}

	private String getGender(int gender) {
		if (gender == 1) {
			return "男";
		}
		if (gender == 2) {
			return "女";
		}
		return "未知";
	}

	private byte getGender(String gender) {
		if (StringUtils.equals(gender, "男")) {
			return 1;
		}
		if (StringUtils.equals(gender, "女")) {
			return 2;
		}
		return 3;
	}
}
