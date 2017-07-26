package com.fhr.osmonitor.event;

import java.util.EventListener;
/**
 * 系统警告监听器
 * @author fhr
 * @date 2017/07/26
 */
public interface WarinListener extends EventListener {
	
	void HandleEvent(WarinEvent event);

}
