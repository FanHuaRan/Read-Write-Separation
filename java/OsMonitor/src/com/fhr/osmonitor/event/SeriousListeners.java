package com.fhr.osmonitor.event;

import java.util.HashSet;
/**
 * 严重监听器集合
 * @author fhr
 * @since 2017/07/26
 */
public class SeriousListeners extends HashSet<SeriousListener> {
	
	private static final long serialVersionUID = -7425020450587598023L;
	
	public void fire(SeriousEvent seriousEvent){
		for(SeriousListener listener:this){
			if(listener!=null){
				listener.HandleEvent(seriousEvent);
			}
		}
	}
	@Override
	public String toString(){
		return String.format("SeriousListeners[size:%d]",this.size());
	}
}
