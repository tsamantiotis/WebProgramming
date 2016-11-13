package sockettcp;

import java.io.*;
import java.net.*;

public class EchoClient {

    public static void main(String[] args) throws IOException {
//        if (args.length != 2) {
//            System.err.println(
//                    "Usage: java EchoClient <host name> <port number>");
//            System.exit(1);
//        }
        String hostName = "localhost";
        int portNumber = 10500;
        try {
            
            String Name;
            String Surname;
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream outToServer = new ObjectOutputStream(echoSocket.getOutputStream());
            //ObjectInputStream infromServer = new ObjectInputStream(echoSocket.getInputStream());
            String userInput;
            System.out.println("Connected! Please insert data:");
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Give your name: ");
                Name=stdIn.readLine();
                System.out.println("Give your Surname: ");
                Surname=stdIn.readLine();
                Person P=new Person(Name,Surname);
                outToServer.writeObject(P); 
                
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
    }
}
