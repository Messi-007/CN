#SERVER
import java.net.*;
import java.io.*;

class UDPServer {
    public static void main(String args[]) throws Exception {
        DatagramSocket ds = new DatagramSocket(2000);
        byte[] b = new byte[1000];

        DatagramPacket dp = new DatagramPacket(b, b.length);
        ds.receive(dp);

        String str = new String(b);
        System.out.println("Received ---> " + str);
    }
}


#CLIENT
import java.net.*;
import java.io.*;

class UDPClient {
    public static void main(String args[]) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        byte[] b = "This is udp client".getBytes();
        int port = 2000;

        InetAddress ip = InetAddress.getByName("localhost");
        DatagramPacket dp = new DatagramPacket(b, b.length, ip, port);

        ds.send(dp);
    }
}
