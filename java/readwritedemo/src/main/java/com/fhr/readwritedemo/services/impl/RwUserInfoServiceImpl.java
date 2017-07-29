package com.fhr.readwritedemo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fhr.readwritedemo.dtomodels.RwUserInfo;
import com.fhr.readwritedemo.services.IRwUserInfoService;
@Service
public class RwUserInfoServiceImpl implements IRwUserInfoService {

	@Override
	public List<RwUserInfo> findAll() {
		return null;
	}

	@Override
	public RwUserInfo findById(Object id) {
		return null;
	}

	@Override
	public void deleteById(Object id) {

	}

	@Override
	public void update(RwUserInfo rwUserInfo) {

	}

	@Override
	public Object save(RwUserInfo rwUserInfo) {
		return null;
	}

	@Override
	public List<RwUserInfo> findByPage(int pageIndex, int count) {
		return null;
	}

}
