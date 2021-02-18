package research.parentThread;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main (String[] args) throws InterruptedException
    {
        CountDownLatch latch = new CountDownLatch(3);
        Date date = new Date();

        Runnable invokable = () -> {
            /*thread body, any code can run here*/
            System.out.println(date.getTime() + " Runnable. ThreadId [" + Thread.currentThread().getId() + "]. ThreadName: " + Thread.currentThread().getName() + ". Process [" + ManagementFactory
                .getRuntimeMXBean().getName() + "] " + "Thread Group: "
                                   + Thread.currentThread().getThreadGroup().getName() + " Parent Group: " + Thread.currentThread().getThreadGroup().getParent().getName());
            latch.countDown();
        };
        Thread t1 = new Thread(invokable,"one");
        Thread t2 = new Thread(invokable,"two");
        Thread t3 = new Thread(invokable,"three");
        t1.start();
        t2.start();
        t3.start();
        latch.await();
        System.out.println( "Hello World!" );
    }
}
