package research.parentThread;

import static org.junit.Assert.assertTrue;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestThreadsByExecutorService {

    private String testName = this.getClass().getName();

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestName() {
        return this.testName;
    }

    @Test
    public void shouldAnswerWithTrue() {
        Date date = new Date();
        int testThreadsPoolSize = 3;
        ExecutorService testThreads = Executors.newFixedThreadPool(testThreadsPoolSize);

        try {
            System.out.println(date.getTime() + " " + this.testName + ". ThreadId [" + Thread.currentThread().getId()
                                   + "]. ThreadName: " + Thread.currentThread().getName()
                                   + ". ParentThread ["
                                   + TestProperties.storage.getProperty(String.valueOf(Thread.currentThread().getId())) + "]"
                                   + ". Process [" + ManagementFactory.getRuntimeMXBean().getName() + "]");
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        }
        assertTrue(true);
        for (int i = 0; testThreadsPoolSize > i; i++) {
            testThreads.submit(() -> {
                try {
                    System.out.println(date.getTime() + " ExecutorService. ThreadId [" + Thread.currentThread().getId()
                                           + "]. ThreadName: " + Thread.currentThread().getName()
                                           + ". ParentThread ["
                                           + TestProperties.storage.getProperty(String.valueOf(Thread.currentThread().getId())) + "]"
                                           + ". Process [" + ManagementFactory.getRuntimeMXBean()
                        .getName() + "] ");
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    System.out.println(date.getTime() + " ExecutorService. Exception");
                }
            });
        }

        System.out.println(TestProperties.storage.toString());

    }
}
