package research.parentThread.testng.tests;

import org.junit.Test;
import research.parentThread.TestThreadsByExecutorService;

public class Test03 {

    String testName = this.getClass().getName();

    @Test
    public void main() throws InterruptedException {
        TestThreadsByExecutorService test = new TestThreadsByExecutorService();
        test.setTestName(testName);
        test.shouldAnswerWithTrue();
    }
}
