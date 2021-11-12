import java.util.HashMap;

public class SharedDataState {
    private static HashMap<String, Integer> ClientAccounts = new HashMap<String, Integer>();
    private boolean accessing = false;
    private int amount;

    public SharedDataState(){
        ClientAccounts.put("Bank Client 1", 1000);
        ClientAccounts.put("Bank Client 2", 1000);
        ClientAccounts.put("Bank Client 3", 1000);
    }

    public synchronized void acquireLock() throws InterruptedException {
        String currentThread = Thread.currentThread().getName();
        System.out.println(currentThread + " is trying to get a lock");
        while(accessing){
            System.out.println(currentThread + " is waiting to get a lock another thread is using");
            wait();
        }
        accessing = true;
        System.out.println(currentThread + " acquired a lock");
    }

    public synchronized void releaseLock() {
        accessing = false;
        notifyAll();
        String currentThread = Thread.currentThread().getName();
        System.out.println(currentThread + " has released a lock");
    }

    public synchronized String processInput(String ClientName, String input) {
        String[] newInput = input.split("\\s+");
        String operation = newInput[0];
        switch (operation) {
            case "Add":
                amount = Integer.parseInt(newInput[1]);
                ClientAccounts.put(ClientName, ClientAccounts.get(ClientName) + amount);
                return ClientName +" now has " + ClientAccounts.get(ClientName);
            case "Subtract":
                amount = Integer.parseInt(newInput[1]);
                ClientAccounts.put(ClientName, ClientAccounts.get(ClientName) - amount);
                return ClientName +" now has " + ClientAccounts.get(ClientName);
            case "Transfer":
                amount = Integer.parseInt(newInput[1]);
            default:
                return "Unrecognised operation";
        }
    }
}
