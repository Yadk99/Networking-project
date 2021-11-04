import java.io.IOException;
import java.net.Socket;

public class BankClient1{
    public static void main(String[] arg) throws IOException {
        Socket BankClientSocket;
        String BankClientName = "localhost";
        String BankClientID = "Bank Client 1";
        int BankSocketNumber = 1;

        BankClientSocket = new Socket(BankClientName, BankSocketNumber);
        System.out.println(BankClientID + " initialised connection to server");
    }
}
