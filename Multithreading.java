#MULTISERVER
import java.io.*;
import java.net.*;

public class MultiEchoServer {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        System.out.println("Server is running and waiting for clients...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Client connected: " + client.getInetAddress());
                new ClientHandler(client).start(); // Handle client in a new thread
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            Scanner input = new Scanner(socket.getInputStream());
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        ) {
            String message;
            do {
                message = input.nextLine();
                System.out.println("Received from client: " + message);
                output.println(message); // Echo back to client
            } while (!message.equalsIgnoreCase("QUIT"));

            System.out.println("Client disconnected: " + socket.getInetAddress());

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Could not close connection.");
            }
        }
    }
}


#MULTICLIENT
import java.io.*;
import java.net.*;
import java.util.*;

public class MultiEchoClient {
    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("\nHost ID not found!\n");
            System.exit(1);
        }

        sendMessages();
    }

    private static void sendMessages() {
        Socket socket = null;

        try {
            socket = new Socket(host, PORT);
            Scanner networkInput = new Scanner(socket.getInputStream());
            PrintWriter networkOutput = new PrintWriter(socket.getOutputStream(), true);
            Scanner userEntry = new Scanner(System.in);

            String message, response;
            do {
                System.out.print("Enter message ('QUIT' to exit): ");
                message = userEntry.nextLine();

                networkOutput.println(message);         // Send message
                response = networkInput.nextLine();     // Receive response
                System.out.println("\nSERVER> " + response);
            } while (!message.equalsIgnoreCase("QUIT"));

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\nClosing connection...");
                if (socket != null)
                    socket.close();
            } catch (IOException ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}
