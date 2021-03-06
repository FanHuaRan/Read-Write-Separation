package com.fhr.readwritedemo.repositorys.impl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fhr.readwritedemo.core.services.ICustomJdbcOperations;
import com.fhr.readwritedemo.models.RwUser;
import com.fhr.readwritedemo.repositorys.IRwUserRepository;
/**
 * 用户数据访问逻辑实现
 * @author fhr
 * @since
 */
@Repository
public class RwUserRepositoryImpl implements IRwUserRepository {
	
	@Autowired
	private ICustomJdbcOperations jdbcOperations=null;
	
	@Override
	public List<RwUser> findAll() throws SQLException {
		List<Map<String,Object>> values=jdbcOperations.findMoreResultByMap("select * from rwuser", null);
		return values.stream()
				.map(value->createRwUser(value))
				.collect(Collectors.toList());
	}

	@Override
	public RwUser findById(Object id) throws SQLException {
		List<Object> params=Arrays.asList(id);
		return createRwUser(jdbcOperations.findSingleResultByMap("select * from rwuser where userid=?",params));
	}

	@Override
	public void deleteById(Object id) throws SQLException {
		jdbcOperations.deleteRecord("rwuser", "userid", id);
	}

	@Override
	public void update(RwUser rwUserInfo) throws SQLException {
		Object id=rwUserInfo.getUserId();
		Map<String,Object> fields=new HashMap<>();
		fields.put("name", rwUserInfo.getName());
		fields.put("age", rwUserInfo.getAge());
		fields.put("gender", rwUserInfo.getGender());
		fields.put("email", rwUserInfo.getEmail());
		jdbcOperations.updateRecordByMap("rwuser", fields, "userid", id);
	}

	@Override
	public Object save(RwUser rwUserInfo) throws SQLException {
		Map<String,Object> fields=new HashMap<>();
		fields.put("name", rwUserInfo.getName());
		fields.put("age", rwUserInfo.getAge());
		fields.put("gender", rwUserInfo.getGender());
		fields.put("email", rwUserInfo.getEmail());
		return jdbcOperations.insertRecordByMap("rwuser", fields);
	}

	@Override
	public List<RwUser> findByPage(int pageIndex, int count) throws SQLException {
		List<Map<String,Object>> values=jdbcOperations.findRecordByPage("rwuser", pageIndex, count, Arrays.asList("userid"), null);
		return values.stream()
				.map(value->createRwUser(value))
				.collect(Collectors.toList());
	}
	
	public RwUser createRwUser(Map<String,Object> value){
		if(value==null){
			return null;
		}
		RwUser user=new RwUser();
		user.setUserId((long)value.get("userid"));
		user.setAge((int)value.get("age"));
		user.setGender((int)value.get("gender"));
		user.setName((String)value.get("name"));
		user.setEmail((String)value.get("email"));
		return user;
	}
	
}
