package com.abnull.yrs.log;

public class Debug {

	String program_name = "";
	
	public Debug(String name)
	{
		program_name = name;
	}
	
	public void print(String content)
	{
		System.out.println("[" + program_name + "] " + content);
	}
}
