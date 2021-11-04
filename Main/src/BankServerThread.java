import java.net.Socket;

public class BankServerThread extends Thread {

    Socket currentBankSocket;
    SharedDataState currentSharedDataStateObject;
    String currentThreadName;

    public BankServerThread(Socket BankSocket, String ThreadName, SharedDataState SharedDataStateObject){
        currentBankSocket = BankSocket;
        currentSharedDataStateObject = SharedDataStateObject;
        currentThreadName = ThreadName;
    }

    public void run() {
        System.out.println(currentThreadName +" is running");
        System.out.println(currentSharedDataStateObject.testNumber);
    }
}
