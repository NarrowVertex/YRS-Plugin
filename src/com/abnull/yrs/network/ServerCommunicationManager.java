package com.abnull.yrs.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

import com.abnull.yrs.YRSModHelpPlugin;

public class ServerCommunicationManager {

	public Socket server;
	public int server_port;

	public BufferedReader server_input;
	public BufferedWriter server_output;
	
	public boolean is_server_connected = false;
	
	public ServerCommunicationManager(int port)
	{
		server_port = port;
		
		ConnectServer connect_server = new ConnectServer(server_port);
		connect_server.start();
	}
	
	public void send_message(String data_name, String data_contents)
	{
		if(is_server_connected == true)
		{
			try {
				server_output.write(data_name + ":" + data_contents + "\r\n");
				server_output.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			YRSModHelpPlugin.debug.print("Server Socket Is Not Connected. . !");
		}
	}
}
