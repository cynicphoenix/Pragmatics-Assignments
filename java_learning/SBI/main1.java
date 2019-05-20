/*
//Source CPU Time : Internet

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

class main {
    public static void main(String args[]) {
        int accounts = 10000, minBalance = 10000, totalTransactions = 10000000, threadsNo, i = 0;
        long startTime, endTime, timeElapsed;
        Random rand = new Random();
        bank SBI = new bank(accounts, minBalance);
        ArrayList<transaction> transactionList = new ArrayList<transaction>();

        while (i < totalTransactions) { // Update TransactionList
            int sender = rand.nextInt(10000);
            int receiver = rand.nextInt(10000);
            if (sender == receiver)
                continue;
            int cash = rand.nextInt(10000);
            transaction temp = new transaction(sender, receiver, cash);
            transactionList.add(temp);
            i++;
        }

        System.out.print("Threads  :  ");
        Scanner sc = new Scanner(System.in);
        threadsNo = sc.nextInt();

        switch (threadsNo) {
        case 1:
            Thread[] threads1 = new Thread[1];
            testThread t1TransactionThreads1 = new testThread(SBI, transactionList, 0, 9999999);
            threads1[0] = new Thread(t1TransactionThreads1);
            startTime = System.nanoTime();
            threads1[0].setDaemon(true);
            threads1[0].start();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            
            for (Thread thread : threads1) System.out.println("Execution Time : " +
                cpuTime(thread));
              
            for (Thread thread : threads1) { try { thread.join(); } catch
                (InterruptedException e) { e.printStackTrace(); } }
             
            System.out.println("Processing Passed!");
            break;
        case 2:
            Thread[] threads2 = new Thread[2];
            testThread t2TransactionThreads1 = new testThread(SBI, transactionList, 0, 4999999);
            threads2[0] = new Thread(t2TransactionThreads1);
            testThread t2TransactionThreads2 = new testThread(SBI, transactionList, 5000000, 9999999);
            threads2[1] = new Thread(t2TransactionThreads2);
            startTime = System.nanoTime();
            threads2[0].setDaemon(true);
            threads2[1].setDaemon(true);
            threads2[0].start();
            threads2[1].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            
             for (Thread thread : threads2) System.out.println("Execution Time : " +
                cpuTime(thread));
              
             for (Thread thread : threads2) { try { thread.join(); } catch
                (InterruptedException e) { e.printStackTrace(); } }
             
            System.out.println("Processing Passed!");
            break;
        case 3:
            Thread[] threads3 = new Thread[3];
            testThread t3TransactionThreads1 = new testThread(SBI, transactionList, 0, 3333332);
            threads3[0] = new Thread(t3TransactionThreads1);
            testThread t3TransactionThreads2 = new testThread(SBI, transactionList, 3333333, 6666666);
            threads3[1] = new Thread(t3TransactionThreads2);
            testThread t3TransactionThreads3 = new testThread(SBI, transactionList, 6666667, 9999999);
            threads3[2] = new Thread(t3TransactionThreads3);
            startTime = System.nanoTime();
            threads3[0].setDaemon(true);
            threads3[1].setDaemon(true);
            threads3[2].setDaemon(true);
            threads3[0].start();
            threads3[1].start();
            threads3[2].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            
            for (Thread thread : threads3) System.out.println("Execution Time : " +
                cpuTime(thread));
             
             for (Thread thread : threads3) { try { thread.join(); } catch
                (InterruptedException e) { e.printStackTrace(); } }
             
            System.out.println("Processing Passed!");
            break;
        case 4:
            Thread[] threads4 = new Thread[4];
            testThread t4TransactionThreads1 = new testThread(SBI, transactionList, 0, 2499999);
            threads4[0] = new Thread(t4TransactionThreads1);
            testThread t4TransactionThreads2 = new testThread(SBI, transactionList, 2500000, 4999999);
            threads4[1] = new Thread(t4TransactionThreads2);
            testThread t4TransactionThreads3 = new testThread(SBI, transactionList, 5000000, 7499999);
            threads4[2] = new Thread(t4TransactionThreads3);
            testThread t4TransactionThreads4 = new testThread(SBI, transactionList, 7500000, 9999999);
            threads4[3] = new Thread(t4TransactionThreads4);
            startTime = System.nanoTime();
            threads4[0].setDaemon(true);
            threads4[1].setDaemon(true);
            threads4[2].setDaemon(true);
            threads4[3].setDaemon(true);
            threads4[0].start();
            threads4[1].start();
            threads4[2].start();
            threads4[3].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            
            for(Thread thread : threads4)
                System.out.println("Execution Time : "+cpuTime(thread));
             
            for(Thread thread : threads4) { try { thread.join(); } catch
                (InterruptedException e) { e.printStackTrace(); } }
             
            System.out.println("Processing Passed!");

            break;
        default:
            startTime = System.nanoTime();
            System.out.println("Enter correct choice!");
        }
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds : " + timeElapsed);
    }

    private static long cpuTime(Thread thr) {
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        if (mxBean.isThreadCpuTimeSupported()) {
            try {
                return mxBean.getThreadCpuTime(thr.getId());
            } catch (UnsupportedOperationException e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("Not supported");
        }
        return 0;
    }
}*/
