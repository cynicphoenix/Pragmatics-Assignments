public class bankAccount {
    private int accountNumber, availableBalance;
    
    bankAccount(int accountNo, int availableBal){
        accountNumber = accountNo;
        availableBalance = availableBal; 
    }

    public int returnAccountNumber() {
        return accountNumber;
    }

    public synchronized int returnAvailableBalance() {
        return availableBalance;
    }

    public synchronized void deposit(int cash) {
        availableBalance += cash;
    }

    public synchronized void withdraw(int cash) {
        availableBalance -= cash;
    }
}