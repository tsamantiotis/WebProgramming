package sockettcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class EchoThread extends Thread{
	Socket socket;
	
	EchoThread(Socket sock){
		super();
		this.socket = sock;
	}
	
	public void run(){
		try{
			
                        PrintWriter outnumber = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			String inputLine;
                        String outputLine;
                        int number;
                        
			while ((inputLine = in.readLine()) != null) {
                            int sum=0;
                            number=Integer.parseInt(inputLine);
                            for(int i=1;i<=number;i++){
                                   sum=sum+i; 
                                }
				System.out.println("Received: " + number);
                                outputLine = "" +sum;
                                outnumber.println(outputLine);
                                System.out.println("Send: " + sum);
                                
                               
			}
			
			socket.close();

		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port or listening for a connection");
			System.out.println(e.getMessage());
		}
		
	}
}
