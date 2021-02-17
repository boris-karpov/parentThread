package research.parentThread;

import static org.junit.Assert.assertTrue;

import java.lang.management.ManagementFactory;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Try to create a new thread
     */
    private String testName = this.getClass().getName();
    public void setTestName(String testName){this.testName = testName;}

    public String getTestName(){
        String testName = this.testName;
        return testName;
    }
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
        try {
            Thread.sleep(1000);
            System.out.println(
                this.testName + ". ThreadId [" + Thread.currentThread().getId() + "]. ThreadName: " + Thread.currentThread().getName() + ". Process [" + ManagementFactory.getRuntimeMXBean().getName()
                    + "] " + "Thread Group: " + Thread.currentThread().getThreadGroup().getName() + " Parent Group: " + Thread.currentThread().getThreadGroup().getParent().getName());
        }
        catch (InterruptedException e) {
        }
    }
}
