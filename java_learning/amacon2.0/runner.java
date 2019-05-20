import org.junit.runner.*;
import java.io.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class runner {
    public static void main(String args[]){
        Result res = JUnitCore.runClasses(testSuit.class);
        for(Failure fail : res.getFailures())
            System.out.println(fail.toString());
    }
}



