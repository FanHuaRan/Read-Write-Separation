package com.fhr.readwritedemo.core.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fhr.readwritedemo.core.services.IPageQueryCreateStrategy;

/**
 * mysql分页查询语句生成实现
 * @author fhr
 * @date 2017/07/31
 */
@Service
public class MysqlPageQueryCreateStrategy implements IPageQueryCreateStrategy {

	@Override
	public String createSimpleQuery(String tableName, int pageIndex, int count, List<String> ascFields,
			List<String> descFields) {
		StringBuilder builder = new StringBuilder(String.format("select * from %s ", tableName));
		List<String> sortStrs = new ArrayList<>();
		if (ascFields != null && !ascFields.isEmpty()) {
			sortStrs.addAll(ascFields.stream().map(p -> String.format("%s asc", p)).collect(Collectors.toList()));
		}
		if (descFields != null && !descFields.isEmpty()) {
			sortStrs.addAll(ascFields.stream().map(p -> String.format("%s desc", p)).collect(Collectors.toList()));
		}
		if (!sortStrs.isEmpty()) {
			builder.append(String.format("order by %s ", sortStrs.stream().collect(Collectors.joining(",", " ", " "))));
		}
		return builder.append(" limit %d,%d", pageIndex * count, count).toString();
	}

}
