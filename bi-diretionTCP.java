#SERVER
import java.io.*;
import java.net.*;

public class MultiServer {
    public static void main(String args[]) throws Exception {
        ServerSocket servsock = new ServerSocket(3000);
        System.out.println("Server Ready");

        Socket sock = servsock.accept();

        BufferedReader keyRead = new BufferedReader(
            new InputStreamReader(System.in)
        );

        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);

        InputStream istream = sock.getInputStream();
        BufferedReader receiveRead = new BufferedReader(
            new InputStreamReader(istream)
        );

        System.out.println("Start the chat, type and press Enter");

        String receiveMsg, sendMsg;
        while (true) {
            if ((receiveMsg = receiveRead.readLine()) != null) {
                System.out.println(receiveMsg);
            }

            sendMsg = keyRead.readLine();
            pwrite.println(sendMsg);
            pwrite.flush();
        }
    }
}


#CLIENT
import java.io.*;
import java.net.*;

public class MultiClient {
    public static void main(String args[]) throws Exception {
        Socket sock = new Socket("127.0.0.1", 3000);

        BufferedReader keyRead = new BufferedReader(
            new InputStreamReader(System.in)
        );

        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);

        InputStream istream = sock.getInputStream();
        BufferedReader receiveRead = new BufferedReader(
            new InputStreamReader(istream)
        );

        System.out.println("Start the chat, and press Enter key:");

        String receiveMsg, sendMsg;
        while (true) {
            sendMsg = keyRead.readLine();
            pwrite.println(sendMsg);
            pwrite.flush();

            if ((receiveMsg = receiveRead.readLine()) != null) {
                System.out.println(receiveMsg);
            }
        }
    }
}
