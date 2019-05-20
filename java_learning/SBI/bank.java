import java.util.ArrayList;

public class bank {
    //Account created as soon as banking software is launched
    private int totalAccount;
    private int minBalance;
    ArrayList<bankAccount> bankAccountList;
    
    bank(int accounts, int minBal) {
        bankAccountList = new ArrayList<bankAccount>();
        totalAccount = accounts;
        minBalance = minBal;
        for(int i = 0; i < totalAccount; i++) {
            bankAccount temp = new bankAccount(i, minBalance);
            bankAccountList.add(temp);
        }
    }

    public synchronized void transaction(int sender, int receiver, int cash){
        if(bankAccountList.get(sender).returnAvailableBalance() < cash)
            return;
        bankAccountList.get(sender).withdraw(cash);
        bankAccountList.get(receiver).deposit(cash);
    }
}