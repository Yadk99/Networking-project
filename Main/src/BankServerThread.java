import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BankServerThread extends Thread {

    private Socket currentBankSocket;
    private SharedDataState currentSharedDataStateObject;
    private String currentThreadName;

    public BankServerThread(Socket BankSocket, String ThreadName, SharedDataState SharedDataStateObject){
        currentBankSocket = BankSocket;
        currentSharedDataStateObject = SharedDataStateObject;
        currentThreadName = ThreadName;
    }

    public void run() {
        try {
            System.out.println(TimeStamp.getTime() +  currentThreadName + " is running");
            PrintWriter out = new PrintWriter(currentBankSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(currentBankSocket.getInputStream()));
            String inputLine, outputLine;

            while ((inputLine = in.readLine()) != null){
                currentSharedDataStateObject.acquireLock(currentThreadName);
                outputLine = currentSharedDataStateObject.processInput(currentThreadName, inputLine);
                out.println(outputLine);
                currentSharedDataStateObject.releaseLock(currentThreadName);
                System.out.println(TimeStamp.getTime() + "Transaction complete");
            }
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
