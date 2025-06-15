#CLIENT
import java.net.*;
import java.io.*;

class BasicClient {
    public static void main(String args[]) {
        try {
            Socket clio = new Socket("localhost", 3000);
            System.out.println("*** Find Server ***");
        } catch (Exception e) { }
    }
}


#SERVER
import java.net.*;
import java.io.*;

class BasicServer {
    public static void main(String args[]) {
        try {
            System.out.println("Waiting for client....");
            ServerSocket serv = new ServerSocket(3000);
            Socket soc = serv.accept();
        } catch (Exception e) { }
    }
}
