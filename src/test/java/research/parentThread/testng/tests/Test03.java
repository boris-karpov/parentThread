package research.parentThread.testng.tests;

import org.junit.Test;
import research.parentThread.ThreadsByExecutorService;

public class Test03 {

    String testName = this.getClass().getName();

    @Test
    public void main() throws InterruptedException {
        ThreadsByExecutorService test = new ThreadsByExecutorService();
        test.setTestName(testName);
        test.shouldAnswerWithTrue();
    }
}
