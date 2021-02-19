
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
    System.out.println("Aspectj Thread Start Hook. Around. ParentThreadId: [" + parentThreadId + "]"
    //+ "The the object ret is: " + ret.toString() //java.lang.NullPointerException
    );
    return ret;
    }
    after() : threadStarting() {
        long newThreadId = Thread.currentThread().getId();
        System.out.println("Aspectj Thread Start Hook. After. CurrentThreadId: ["+ newThreadId + "]");
//        TestProperties.storage.setProperty(String.valueOf(newThreadId),String.valueOf(parentThreadId));
    }

}
