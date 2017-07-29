package com.fhr.readwritedemo.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fhr.readwritedemo.dtomodels.RwUserInfo;
import com.fhr.readwritedemo.services.IRwUserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 用户API控制器
 * 
 * @author fhr
 * @date 2017/07/29
 */
@RestController
@RequestMapping("/users")
@Api(value = "/group", description = "群组的相关操作")
public class RwUserController {

	@Autowired
	private IRwUserInfoService rwUserInfoService;

	@ApiOperation(notes = "findall", httpMethod = "Get", value = "查询所有用户信息")
	@ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input") })
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<RwUserInfo> findAll() {
		return rwUserInfoService.findAll();
	}

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public RwUserInfo findById(Object id) {
		return rwUserInfoService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(Object id) {
		rwUserInfoService.deleteById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void update(RwUserInfo rwUserInfo) {
		rwUserInfoService.update(rwUserInfo);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public RwUserInfo save(RwUserInfo rwUserInfo) {
		Object id = rwUserInfoService.save(rwUserInfo);
		rwUserInfo.setUserId((int) id);
		return rwUserInfo;
	}

	@RequestMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<RwUserInfo> findByPage(int pageIndex, int count) {
		return rwUserInfoService.findByPage(pageIndex, count);
	}
	// 一定要实现分页查询
}
