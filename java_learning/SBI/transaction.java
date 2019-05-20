public class transaction {
    private int sender, receiver, cash;

    public transaction(int sen, int rec, int cas){
        sender = sen;
        receiver = rec;
        cash = cas;
    }

    public int returnSender() {
        return sender;
    }

    public int returnReceiver() {
        return receiver;
    }

    public int returnCash() {
        return cash;
    }
}