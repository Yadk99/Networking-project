import java.io.IOException;
import java.net.ServerSocket;

/*
    Professor Simon Taylor
    Adapted from ActionServer code
     */
public class BankServer {
    public static void main(String[] arg) throws IOException {
        String BankServerName = "WBA";
        int BankServerSocketNumber = 1;
        boolean listen = true;

        SharedDataState SharedDataStateObject = new SharedDataState();

        ServerSocket BankServerSocket = new ServerSocket(BankServerSocketNumber);
        System.out.println(TimeStamp.getTime() + BankServerName + " has started on Port " + BankServerSocket.getLocalPort());

        while(listen){
            new BankServerThread(BankServerSocket.accept(), "Bank Client 1", SharedDataStateObject).start();
            new BankServerThread(BankServerSocket.accept(), "Bank Client 2", SharedDataStateObject).start();
            new BankServerThread(BankServerSocket.accept(), "Bank Client 3", SharedDataStateObject).start();
        }
    }
}
