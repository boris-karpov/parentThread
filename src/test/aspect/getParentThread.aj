
import research.parentThread.TestProperties;

public aspect getParentThread {
    pointcut threadStarting(): call(public void start());
/*
    call(* java.lang.Thread+.start()) ||
                call(* ExecutorService+.execute(..)) ||
                call(* ExecutorService+.submit(..)) ||
                call(* ExecutorService+.invokeAll(..)) ||
                call(* ExecutorService+.invokeAny(..)) ||
                call(* ScheduledExecutorService.scheduleAtFixedRate(..));
*/
    Object around(): threadStarting() {
    long parentThreadId = Thread.currentThread().getId();
    Object ret = proceed();
    long newThreadId = Thread.currentThread().getId();
    TestProperties.storage.setProperty(String.valueOf(newThreadId),String.valueOf(parentThreadId));
    System.out.println("Aspectj Thread Start Hook. ParentThreadId: [" + parentThreadId + "]"
    //+ "The class of the object ret is: " + ret.getClass().getName() //java.lang.NullPointerException
    );
    return ret;
    }

}
