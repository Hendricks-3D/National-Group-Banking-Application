package NGB.server;

public class NGBServerDriver {

	public static void main(String[] args) {
		
		SingleThreadedServer server = new SingleThreadedServer();
		
		System.out.println("Initializing Server...");
		server.initServer();
		
		System.out.println("Starting Server...");
		server.waitForRequest();

	}

}
