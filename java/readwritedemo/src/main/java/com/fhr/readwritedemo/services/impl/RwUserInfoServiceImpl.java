package com.fhr.readwritedemo.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fhr.readwritedemo.dtoconverters.RwUserInfoConverter;
import com.fhr.readwritedemo.dtomodels.RwUserInfo;
import com.fhr.readwritedemo.exceptions.ErrorException;
import com.fhr.readwritedemo.exceptions.NotFoundException;
import com.fhr.readwritedemo.models.RwUser;
import com.fhr.readwritedemo.repositorys.IRwUserRepository;
import com.fhr.readwritedemo.services.IRwUserInfoService;

/**
 * rwuser服务实现（数据访问、事务处理、错误处理的大一统）
 * @author fhr
 * @since 2017/07/31
 */
@Service
public class RwUserInfoServiceImpl implements IRwUserInfoService {
	@Autowired
	private IRwUserRepository rwUserRepository = null;
	@Autowired
	private RwUserInfoConverter rwUserInfoConverter = null;

	@Override
	public List<RwUserInfo> findAll() {
		List<RwUser> rwUsers = null;
		try {
			rwUsers = rwUserRepository.findAll();
		} catch (SQLException e) {
			throw new ErrorException(HttpStatus.BAD_GATEWAY, "数据库连接异常");
		}
		return rwUserInfoConverter.convert(rwUsers);
	}

	@Override
	public RwUserInfo findById(Object id) {
		try {
			RwUser rwUser = rwUserRepository.findById(id);
			if (rwUser == null) {
				throw new NotFoundException(HttpStatus.NOT_FOUND, "未找到该用用户信息");
			}
			return rwUserInfoConverter.convert(rwUser);
		} catch (SQLException exception) {
			throw new ErrorException(HttpStatus.BAD_GATEWAY, "数据库连接异常");
		}
	}

	@Override
	public void deleteById(Object id) {
		try {
			rwUserRepository.deleteById(id);
		} catch (SQLException exception) {
			throw new ErrorException(HttpStatus.BAD_GATEWAY, "数据库连接异常");
		}
	}

	@Override
	public void update(RwUserInfo rwUserInfo) {
		try {
			rwUserRepository.update(rwUserInfoConverter.convert(rwUserInfo));
		} catch (SQLException exception) {
			throw new ErrorException(HttpStatus.BAD_GATEWAY, "数据库连接异常");
		}
	}

	@Override
	public Object save(RwUserInfo rwUserInfo) {
		try {
			return rwUserRepository.save(rwUserInfoConverter.convert(rwUserInfo));
		} catch (SQLException exception) {
			throw new ErrorException(HttpStatus.BAD_GATEWAY, "数据库连接异常");
		}
	}

	@Override
	public List<RwUserInfo> findByPage(int pageIndex, int count) {
		try {
			return rwUserInfoConverter.convert(rwUserRepository.findByPage(pageIndex, count));
		} catch (SQLException exception) {
			throw new ErrorException(HttpStatus.BAD_GATEWAY, "数据库连接异常");
		}
	}

}
