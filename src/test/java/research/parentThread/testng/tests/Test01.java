package testng.tests;

import org.junit.Test;
import research.parentThread.AppTest;

public class Test01 {

    String testName = this.getClass().getName();
    @Test
    public void main(){
        AppTest test = new AppTest();
        test.setTestName(testName);
        test.shouldAnswerWithTrue();
    }
}
