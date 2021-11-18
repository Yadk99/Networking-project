import java.util.HashMap;

public class SharedDataState {
    private static HashMap<String, Integer> ClientAccounts = new HashMap<String, Integer>();
    private boolean accessing = false;
    private int amount;
    private int transferTo;

    public SharedDataState(){
        ClientAccounts.put("Bank Client 1", 1000);
        ClientAccounts.put("Bank Client 2", 1000);
        ClientAccounts.put("Bank Client 3", 1000);
    }

    /*
    Professor Simon Taylor
    acquireLock and releaseLock functions adapted from SharedActionState
     */
    public synchronized void acquireLock() throws InterruptedException {
        String currentThread = Thread.currentThread().getName();
        System.out.println(TimeStamp.getTime() +  currentThread + " is trying to get a lock");
        while(accessing){
            System.out.println(TimeStamp.getTime() +  currentThread + " is waiting to get a lock another thread is using");
            wait();
        }
        accessing = true;
        System.out.println(TimeStamp.getTime() +  currentThread + " acquired a lock");
    }

    public synchronized void releaseLock() {
        accessing = false;
        notifyAll();
        String currentThread = Thread.currentThread().getName();
        System.out.println(TimeStamp.getTime() +  currentThread + " has released a lock");
    }

    public synchronized String processInput(String ClientName, String input) {
        String[] newInput = input.split("\\s+");
        String operation = newInput[0];
        switch (operation) {
            case "Add":
                amount = Integer.parseInt(newInput[1]);
                ClientAccounts.put(ClientName, ClientAccounts.get(ClientName) + amount);
                return  TimeStamp.getTime() + ClientName +" now has " + ClientAccounts.get(ClientName);
            case "Subtract":
                amount = Integer.parseInt(newInput[1]);
                ClientAccounts.put(ClientName, ClientAccounts.get(ClientName) - amount);
                return TimeStamp.getTime() +  ClientName +" now has " + ClientAccounts.get(ClientName);
            case "Transfer":
                amount = Integer.parseInt(newInput[1]);
                transferTo = Integer.parseInt(newInput[2]);
                switch (transferTo) {
                    case 1:
                        ClientAccounts.put(ClientName, ClientAccounts.get(ClientName) - amount);
                        ClientAccounts.put("Bank Client 1", ClientAccounts.get("Bank Client 1") + amount);
                        return  TimeStamp.getTime() +  ClientName + " has transferred " + amount + " to Bank Client 1, " +
                                ClientName + " now has " + ClientAccounts.get(ClientName);
                    case 2:
                        ClientAccounts.put(ClientName, ClientAccounts.get(ClientName) - amount);
                        ClientAccounts.put("Bank Client 2", ClientAccounts.get("Bank Client 2") + amount);
                        return TimeStamp.getTime() +  ClientName + " has transferred " + amount + " to Bank Client 2, " +
                                ClientName + " now has " + ClientAccounts.get(ClientName);
                    case 3:
                        ClientAccounts.put(ClientName, ClientAccounts.get(ClientName) - amount);
                        ClientAccounts.put("Bank Client 3", ClientAccounts.get("Bank Client 3") + amount);
                        return TimeStamp.getTime() +  ClientName + " has transferred " + amount + " to Bank Client 3, " +
                                ClientName + " now has " + ClientAccounts.get(ClientName);
                    default:
                        return TimeStamp.getTime() +  "Unrecognised destination client for transfer";
                }
            default:
                return TimeStamp.getTime() +  "Unrecognised operation";
        }
    }
}
