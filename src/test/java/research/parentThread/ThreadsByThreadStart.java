package research.parentThread;

import static org.junit.Assert.assertTrue;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ThreadsByThreadStart {

    private String testName = this.getClass().getName();

    public void setTestName(String testName) {
        this.testName = testName;
    }

    CountDownLatch latch = new CountDownLatch(3);
    Date date = new Date();

    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
        Runnable invokable = () -> {
            /*thread body, any code can run here*/
            System.out.println(date.getTime() + " Runnable, Thread.start(). ThreadName: " + Thread.currentThread().getName()
                                   + ". ThreadId [" + Thread.currentThread().getId() + "]"
                                   + ". ParentThread ["
                                   + TestProperties.storage.getProperty(String.valueOf(Thread.currentThread().getId())) + "]"
                                   + ". PrimaryThreadId ["
                                   + TestProperties.getPrimaryThreadId(String.valueOf(Thread.currentThread().getId())) + "]"
                                   + ". Process [" + ManagementFactory.getRuntimeMXBean().getName() + "]");
            latch.countDown();
        };

        assertTrue(true);
        try {
            Thread.sleep(1000);
            System.out.println(date.getTime() + " " + this.testName + ".  ThreadName: " + Thread.currentThread().getName()
                                   + ". ThreadId [" + Thread.currentThread().getId() + "]"
                                   + ". ParentThreadId ["
                                   + TestProperties.storage.getProperty(String.valueOf(Thread.currentThread().getId())) + "]"
                                   + ". PrimaryThreadId ["
                                   + TestProperties.getPrimaryThreadId(String.valueOf(Thread.currentThread().getId())) + "]"
                                   + ". Process [" + ManagementFactory.getRuntimeMXBean().getName() + "]");
        }
        catch (InterruptedException e) {
        }
        Thread t1 = new Thread(invokable, "  one");
        Thread t2 = new Thread(invokable, "  two");
        Thread t3 = new Thread(invokable, "three");
        t1.start();
        t2.start();
        t3.start();
        latch.await();
    }
}
