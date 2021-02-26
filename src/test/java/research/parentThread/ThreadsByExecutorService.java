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
public class ThreadsByExecutorService {

    private String testName = this.getClass().getName();

    public ThreadsByExecutorService() {
        testThreadsPoolSize = 3;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    private final int testThreadsPoolSize;



    @Test
    public void shouldAnswerWithTrue() {
        Date date = new Date();

        //Line to add
        long parentThreadId = Thread.currentThread().getId();
        ExecutorService testThreads = Executors.newFixedThreadPool(testThreadsPoolSize);

        try {
            System.out.println(//date.getTime() + " " +
                               this.testName.substring(this.testName.length() - 38)
                                   + ".  ThreadName: " + Thread.currentThread().getName()
                                   + ". ThreadId [" + Thread.currentThread().getId() + "]"
                                   + ". ParentThread ["
                                   + TestProperties.storage.getProperty(String.valueOf(Thread.currentThread().getId())) + "]"
                                   + ". PrimaryThreadId ["
                                   + TestProperties.getPrimaryThreadId(String.valueOf(Thread.currentThread().getId())) + "]"
                                   + ". Process [" + ManagementFactory.getRuntimeMXBean().getName() + "]");
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        }
        assertTrue(true);
        for (int i = 0; testThreadsPoolSize > i; i++) {
            testThreads.submit(() -> {
                try {
                    //Line to add
                    TestProperties.storage.setProperty(String.valueOf(Thread.currentThread().getId()),String.valueOf(parentThreadId));
                    System.out.println(date.getTime() + " ExecutorService.submit().  ThreadName: " + Thread.currentThread().getName()
                                           + ". ThreadId [" + Thread.currentThread().getId() + "]"
                                           + ". ParentThread ["
                                           + TestProperties.storage.getProperty(String.valueOf(Thread.currentThread().getId())) + "]"
                                           + ". PrimaryThreadId ["
                                           + TestProperties.getPrimaryThreadId(String.valueOf(Thread.currentThread().getId())) + "]"
                                           + ". Process [" + ManagementFactory.getRuntimeMXBean()
                        .getName() + "] ");
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                }
            });
        }
    }
}
