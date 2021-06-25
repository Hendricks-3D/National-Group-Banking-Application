package NGB.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedServer {
	
	private ServerSocket servSocket;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public SingleThreadedServer()
	{
		
	}
	
	public void getStream()
	{
		try
		{
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
	}
	

	public void closeConection()
	{
		try {
			oos.close();
			ois.close();
			socket.close();
		}catch(IOException ex)
		{
			
			ex.printStackTrace();
		}
	}
	
	
	public void initServer() {
		try 
		{
			servSocket = new ServerSocket(8888,1);
			
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	public void waitForRequest()
	{
		if(servSocket==null)
		{
			System.out.println("Server not initialized");
			return;
		}
		
		try
		{
			while(true)
			{
				socket = servSocket.accept();
				this.getStream();
				
				String action = "";
				try
				{
					do {
						
						try
						{
							action = (String) ois.readObject();
							
							if(action.equals("register"))
							{
								oos.writeObject("Odain is Registered");
							}
						}catch(ClassCastException | ClassNotFoundException ex)
						{
							ex.printStackTrace();
						}
						
						
					}while(!action.equals("EXIT"));
				}
				catch(EOFException ex)
				{
					System.out.println("Client is  no longer communicating with server");
				}

				this.closeConection();
			}
			
			
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		
	}
}
