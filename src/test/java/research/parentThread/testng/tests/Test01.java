package research.parentThread.testng.tests;

import org.junit.Test;
import research.parentThread.TreeOfThreads01;

public class Test01 {

    String testName = this.getClass().getName();
    @Test
    public void main() throws InterruptedException {
        TreeOfThreads01 test = new TreeOfThreads01();
        test.setTestName(testName);
        test.shouldAnswerWithTrue();
    }
}
