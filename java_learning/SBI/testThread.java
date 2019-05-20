import java.util.ArrayList;

public class testThread implements Runnable {
    int start, end;
    ArrayList<transaction> transactionList = new ArrayList<transaction>();
    bank SBI;
    
    testThread(bank tempBank, ArrayList<transaction> tempList, int s, int e){
        SBI = tempBank;
        transactionList = tempList;
        start = s;
        end = e;
    }

    public void run() {
        for(int i = start; i <= end; i++)
            SBI.transaction(transactionList.get(i).returnSender(), transactionList.get(i).returnReceiver(), transactionList.get(i).returnCash());
    }
}