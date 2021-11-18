import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BankClient2{
    public static void main(String[] arg) throws IOException {
        Socket BankClientSocket;
        String BankClientName = "localhost";
        String BankClientID = "Bank Client 2";
        int BankSocketNumber = 1;
        BufferedReader received, input;
        PrintWriter send;
        boolean communicating = false;

        BankClientSocket = new Socket(BankClientName, BankSocketNumber);
        System.out.println(TimeStamp.getTime() + BankClientID + " initialised connection to server");
        communicating = true;
        received = new BufferedReader(new InputStreamReader(BankClientSocket.getInputStream()));
        send = new PrintWriter(BankClientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(System.in));

        //Code logic adapted from ActionServer code
        while(communicating){
            System.out.println(TimeStamp.getTime() + "Choose operation: Add, Subtract, Transfer; amount of money;" +
                    "if transferring write the number of the client you are transferring to");
            String fromClient = input.readLine();
            if (fromClient != null) {
                System.out.println(TimeStamp.getTime() +  "Sending message");
                send.println(fromClient);
            }
            System.out.println(received.readLine());
            System.out.println(TimeStamp.getTime() +  "Transaction complete");
        }
    }
}
