package com.fhr.osmonitor.event;

import java.util.EventObject;

import com.fhr.osmonitor.models.OSystemInfo;
/**
 * 操作系统检测信息严重事件
 * @author fhr
 * @date 2017/07/26
 */
public class SeriousEvent extends EventObject{
	private static final long serialVersionUID = -5630893305618498518L;

	public SeriousEvent(OSystemInfo source) {
		super(source);
	}
}
