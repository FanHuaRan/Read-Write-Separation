package com.fhr.readwritedemo.repositorys.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fhr.readwritedemo.models.RwUser;
import com.fhr.readwritedemo.repositorys.IRwUserRepository;
@Repository
public class RwUserRepositoryImpl implements IRwUserRepository {

	@Override
	public List<RwUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RwUser findById(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Object id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(RwUser rwUserInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object save(RwUser rwUserInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RwUser> findByPage(int pageIndex, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
