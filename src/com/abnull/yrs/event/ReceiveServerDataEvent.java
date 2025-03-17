package com.abnull.yrs.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ReceiveServerDataEvent extends Event{

	private static final HandlerList handlers = new HandlerList();
	
	public String data_label;
	public String data_contents;
	
	public ReceiveServerDataEvent(String label, String contents)
	{
		data_label = label;
		data_contents = contents;
	}
	
	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}