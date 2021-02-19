package research.parentThread;

import static org.junit.Assert.assertTrue;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private String testName = this.getClass().getName();

    public void setTestName(String testName) {
        this.testName = testName;
    }

    CountDownLatch latch = new CountDownLatch(3);
    Date date = new Date();

    public String getTestName() {
        String testName = this.testName;
        return testName;
    }

    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
        //
        // !!! Developers would insert the following line(1)
        long parentThreadId = Thread.currentThread().getId();
        //
        Runnable invokable = () -> {
            //
            // !!! Developers would insert the following line(2)
            TestProperties.storage.setProperty(String.valueOf(Thread.currentThread().getId()), String.valueOf(parentThreadId));
            //
            /*thread body, any code can run here*/
            System.out.println(
                date.getTime() + " Runnable. ThreadId [" + Thread.currentThread().getId() + "]. ThreadName: " + Thread.currentThread().getName() + ". Process [" + ManagementFactory.getRuntimeMXBean()
                    .getName() + "] " + "Thread Group: " + Thread.currentThread().getThreadGroup().getName() + " Parent Group: " + Thread.currentThread().getThreadGroup().getParent().getName());
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
        System.out.println(TestProperties.storage.toString());
        Thread t1 = new Thread(invokable, "one");
        Thread t2 = new Thread(invokable, "two");
        Thread t3 = new Thread(invokable, "three");
        t1.start();
        t2.start();
        t3.start();
        latch.await();
    }
}
