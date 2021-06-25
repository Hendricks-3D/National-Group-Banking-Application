package NGB.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class NGBClient {

	private Socket socket;
	private ObjectOutputStream  oos;
	private ObjectInputStream ois;
	
	
	public boolean initConnection()
	{
		boolean success = false;
		try
		{
			socket = new Socket(InetAddress.getLocalHost(),8888);
			getStreams();
			success = true;
			
		}catch(IOException ex)
		{
			ex.printStackTrace();
			
		}
		return success;
		
	}
	
	
	public void getStreams()
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
			oos.writeObject("EXIT");
			oos.close();
			ois.close();
			socket.close();
			
			System.out.println("Close connection");
		}catch(IOException ex)
		{
			
			ex.printStackTrace();
		}
	}
	
	
	public String register()
	{
		String todaysSpecial = "Not registered";
		try
		{
			
			oos.writeObject("register");
			todaysSpecial = (String) ois.readObject();
			
		}catch(NullPointerException ex)
		{
			ex.printStackTrace();
		}catch(IOException ex)
		{
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return todaysSpecial;
	}
	
	
	
}
