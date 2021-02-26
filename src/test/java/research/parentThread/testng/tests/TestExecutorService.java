package research.parentThread.testng.tests;

import org.junit.Test;
import research.parentThread.ThreadsByExecutorService;

public class TestExecutorService {

    String testName = this.getClass().getName();

    @Test
    public void runTest() {
        ThreadsByExecutorService test = new ThreadsByExecutorService();
        test.setTestName(testName);
        test.shouldAnswerWithTrue();
    }
}
