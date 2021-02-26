package research.parentThread.testng.tests;

import org.junit.Test;
import research.parentThread.TreeOfThreads02;

public class Test02 {

    String testName = this.getClass().getName();
    @Test
    public void runTest() throws InterruptedException {
        TreeOfThreads02 test02 = new TreeOfThreads02();
        test02.setTestName(testName);
        test02.runTest();
    }
}
