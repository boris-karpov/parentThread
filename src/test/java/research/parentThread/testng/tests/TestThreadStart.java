package research.parentThread.testng.tests;

import org.junit.Test;
import research.parentThread.ThreadsByThreadStart;

public class TestThreadStart {

    String testName = this.getClass().getName();
    @Test
    public void runTest() throws InterruptedException {
        ThreadsByThreadStart test = new ThreadsByThreadStart();
        test.setTestName(testName);
        test.shouldAnswerWithTrue();
    }
}
