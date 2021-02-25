package research.parentThread;

import static org.junit.Assert.assertTrue;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TreeOfThreads01 {

    private String testName = this.getClass().getName();

    public void setTestName(String testName) {
        this.testName = testName;
    }

    CountDownLatch latch = new CountDownLatch(3);
    Date date = new Date();

    public String getTestName() {
        return this.testName;
    }

    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
        Runnable invokable = () -> {
            /*thread body, any code can run here*/
            System.out.println(
                date.getTime() + " Runnable, Thread.start(). ThreadId [" + Thread.currentThread().getId() + "]. ThreadName: " + Thread.currentThread().getName()
                    + ". ParentThread [" + TestProperties.storage.getProperty(String.valueOf(Thread.currentThread().getId())) + "]"
                    + ". Process [" + ManagementFactory.getRuntimeMXBean().getName() + "]") ;
            ThreadsByExecutorService test = new ThreadsByExecutorService();
            test.shouldAnswerWithTrue();
            latch.countDown();
        };

        assertTrue(true);
        try {
            Thread.sleep(1000);
            System.out.println(
                this.testName + ". ThreadId [" + Thread.currentThread().getId() + "]. ThreadName: " + Thread.currentThread().getName() + ". Process [" + ManagementFactory.getRuntimeMXBean().getName()
                    + "] " + "Thread Group: " + Thread.currentThread().getThreadGroup().getName() + " Parent Group: " + Thread.currentThread().getThreadGroup().getParent().getName());
        }
        catch (InterruptedException e) {
        }
        Thread t1 = new Thread(invokable, "  tree.one");
        Thread t2 = new Thread(invokable, "  tree.two");
        Thread t3 = new Thread(invokable, "tree.three");
        t1.start();
        t2.start();
        t3.start();
        latch.await();
    }
}
