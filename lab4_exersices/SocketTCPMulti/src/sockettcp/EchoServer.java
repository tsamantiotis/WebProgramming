package sockettcp;

import java.net.*;
import java.io.*;

public class EchoServer {

	public static void main(String[] args) throws IOException {
//		if (args.length != 1) {
//			System.err.println("Usage: java EchoServer <port number>");
//			System.exit(1);
//		}
		int portNumber = 10500;
		try{
			ServerSocket serverSocket = new ServerSocket(portNumber);
			
			while(true){
				Socket clientSocket = serverSocket.accept();
				EchoThread et = new EchoThread(clientSocket);
				et.start();
			}
			
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}
