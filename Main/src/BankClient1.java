import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BankClient1{
    public static void main(String[] arg) throws IOException {
        Socket BankClientSocket;
        String BankClientName = "localhost";
        String BankClientID = "Bank Client 1";
        int BankSocketNumber = 1;
        BufferedReader received, input;
        PrintWriter send;
        boolean communicating = false;

        BankClientSocket = new Socket(BankClientName, BankSocketNumber);
        System.out.println(BankClientID + " initialised connection to server");
        communicating = true;
        received = new BufferedReader(new InputStreamReader(BankClientSocket.getInputStream()));
        send = new PrintWriter(BankClientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(System.in));

        //Code logic adapted from ActionServer code
        while(communicating){
            String fromClient = input.readLine();
            if (fromClient != null) {
                System.out.println("Sending message");
                send.println(fromClient);
            }
            System.out.println(received.readLine());
            System.out.println("Message received from server");
        }
    }
}
