package com.abnull.yrs.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.bukkit.Bukkit;

import com.abnull.yrs.YRSModHelpPlugin;
import com.abnull.yrs.event.ConnectServerEvent;

public class ConnectServer extends Thread {

	Socket socket;
	int port;
	
	BufferedReader input;
	BufferedWriter output;
	
	public ConnectServer(int p)
	{
		port = p;
	}
	
	public void run()
	{
		try{
			YRSModHelpPlugin.debug.print("Requesting to Connect Server. . .");
			// socket = new Socket(InetAddress.getLocalHost(), port);
			socket = new Socket("127.0.0.1", port);
			YRSModHelpPlugin.debug.print("Success to Connect Server. . !");
			
			YRSModHelpPlugin.debug.print("Get Streams From The Socket. . .");
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			YRSModHelpPlugin.debug.print("Initializing Server Communication Manager's Variables. . .");
			YRSModHelpPlugin.server_communication_manager.server = socket;
			YRSModHelpPlugin.server_communication_manager.server_input = input;
			YRSModHelpPlugin.server_communication_manager.server_output = output;
			YRSModHelpPlugin.server_communication_manager.is_server_connected = true;
			
			Bukkit.getPluginManager().callEvent(new ConnectServerEvent(input, output));
			
			YRSModHelpPlugin.debug.print("Transform Thread To Recving From Connecting. . .");
			RecvServerData recv_server_data = new RecvServerData(input);
			recv_server_data.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
