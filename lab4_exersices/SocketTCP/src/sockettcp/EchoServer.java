package sockettcp;

import java.net.*;
import java.io.*;

public class EchoServer {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		if (args.length != 1) {
//                	System.err.println("Usage: java EchoServer <port number>");
//			System.exit(1);
//		}
		int portNumber = 10500;
		try{
                        
			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
			String inputLine;
                        //String Name;
                        Person P=null;
                        //ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                        ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());
			while (in.readLine() != null) {
                               
                                P=(Person) inFromClient.readObject();
				System.out.println("Received Name is: " + P.getName());
                                System.out.println("Received Surname is: " + P.getSurname());
                                
				
			}
			clientSocket.close();
			serverSocket.close();
			
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}
