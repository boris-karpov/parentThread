package research.parentThread.testng.tests;

import org.junit.Test;
import research.parentThread.ThreadsByThreadStart;

public class Test02 {

    String testName = this.getClass().getName();
    @Test
    public void main() throws InterruptedException {
        ThreadsByThreadStart test = new ThreadsByThreadStart();
        test.setTestName(testName);
        test.shouldAnswerWithTrue();
    }
}
