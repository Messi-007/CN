#SERVER
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        boolean bye = false;

        while (true) {
            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer = new byte[1024];

            DatagramPacket recvPkt = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            serverSocket.receive(recvPkt);

            InetAddress IP = recvPkt.getAddress();
            int portNo = recvPkt.getPort();

            String clientData = new String(recvPkt.getData()).trim();
            System.out.println("\nClient: " + clientData);
            System.out.print("Server: ");

            BufferedReader serverRead = new BufferedReader(new InputStreamReader(System.in));
            String serverData = serverRead.readLine();
            sendBuffer = serverData.getBytes();

            DatagramPacket sendPkt = new DatagramPacket(sendBuffer, sendBuffer.length, IP, portNo);
            serverSocket.send(sendPkt);

            if (serverData.equalsIgnoreCase("bye")) {
                System.out.println("Connection ended by server");
                break;
            }
        }

        serverSocket.close();
    }
}


#CLIENT
  import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws SocketException, IOException {
        BufferedReader clientRead = new BufferedReader(new InputStreamReader(System.in));
        InetAddress IP = InetAddress.getByName("127.0.0.1");
        DatagramSocket clientSocket = new DatagramSocket();

        while (true) {
            byte[] sendBuffer = new byte[1024];
            byte[] receiveBuffer = new byte[1024];

            System.out.print("\nClient: ");
            String clientData = clientRead.readLine();
            sendBuffer = clientData.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, IP, 9876);
            clientSocket.send(sendPacket);

            if (clientData.equalsIgnoreCase("bye")) {
                System.out.println("Connection ended by client");
                break;
            }

            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);
            String serverData = new String(receivePacket.getData()).trim();
            System.out.println("Server: " + serverData);
        }

        clientSocket.close();
    }
}
