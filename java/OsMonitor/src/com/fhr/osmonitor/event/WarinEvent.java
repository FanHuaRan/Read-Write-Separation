package com.fhr.osmonitor.event;

import java.util.EventObject;

import com.fhr.osmonitor.models.OSystemInfo;
/**
 * 操作系统检测信息警告事件
 * @author fhr
 * @since 2017/07/26
 */
public class WarinEvent extends EventObject {
	private static final long serialVersionUID = -4284628516011986757L;

	public WarinEvent(OSystemInfo source) {
		super(source);
	}
	@Override
	public String toString(){
		OSystemInfo info=(OSystemInfo)this.source; 
		return String.format("warin event[memoryratio:%d,cpuratio:%d]",info.getMemoryRatio(),info.getCpuRatio());
	}
}
