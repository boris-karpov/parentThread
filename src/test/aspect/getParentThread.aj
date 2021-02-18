
import research.parentThread.TestProperties;

public aspect getParentThread {
    pointcut threadStarting():
    call(* java.lang.Thread+.start()) ||
                call(* ExecutorService+.execute(..)) ||
                call(* ExecutorService+.submit(..)) ||
                call(* ExecutorService+.invokeAll(..)) ||
                call(* ExecutorService+.invokeAny(..)) ||
                call(* ScheduledExecutorService.scheduleAtFixedRate(..));
    Object around(): threadStarting() {
    long parentThreadId = Thread.currentThread().getId();
    Object ret = proceed();
    long newThreadId = Thread.currentThread().getId();
    TestProperties.storage.setProperty(String.valueOf(newThreadId),String.valueOf(parentThreadId));
    System.out.println("Aspectj Thread Start Hook. ParentThreadId: [" + parentThreadId + "]");
    return ret;
    }

}
