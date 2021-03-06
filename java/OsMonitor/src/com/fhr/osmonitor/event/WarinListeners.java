package com.fhr.osmonitor.event;

import java.util.HashSet;
/**
 * 警告监听器集合
 * @author fhr
 * @since 2017/07/26
 */
public class WarinListeners extends HashSet<WarinListener> {
	private static final long serialVersionUID = -8530593809598308677L;
	
	public void fire(WarinEvent warinEvent){
		for(WarinListener listener:this){
			if(listener!=null){
				listener.HandleEvent(warinEvent);
			}
		}
	}
	
	@Override
	public String toString(){
		return String.format("WarinListeners[size:%d]",this.size());
	}
}
