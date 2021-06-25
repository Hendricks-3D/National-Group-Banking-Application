package NGB.client;

public class NGBClientDriver {

	public static void main(String[] args) {
		
		NGBClient client = new NGBClient();
		
		if(client.initConnection())
		{
			System.out.println("client Connection successful");
			
			System.out.println("Registered?: " + client.register());
			
			
			client.closeConection();
		}


	}

}
