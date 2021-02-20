
import java.util.concurrent.ExecutorService;
import research.parentThread.TestProperties;

public aspect getParentThread {
    pointcut threadStarting(Thread t): call(* java.lang.Thread+.start()) && target(t) ;
    Object around(Thread t): threadStarting(t) {
        long parentThreadId = Thread.currentThread().getId();
        Object ret = proceed(t);
        TestProperties.storage.setProperty(String.valueOf(t.getId()),String.valueOf(parentThreadId));
        System.out.println("Aspectj Thread Start Hook, around. ParentThreadId: [" + parentThreadId + "]" + " Target Id is: [" + t.getId() + "]");
        return ret;
    }
pointcut threadPooling(ExecutorService s, Runnable r): call(* submit(..)) && target(s) && args(r);
    Object around(ExecutorService s, Runnable r): threadPooling(s,r) {
        long parentThreadId = Thread.currentThread().getId();
        Object ret = proceed(s,r);
        //TestProperties.storage.setProperty(String.valueOf(t.getId()),String.valueOf(parentThreadId));
        System.out.println("Aspectj Thread Pooling Hook, around. ExecutorService.submit. ParentThreadId: [" + parentThreadId + "]"
        + " ClassName of the task is: [" + r.getClass().getName() + "]"
         );
        return ret;
    }

}
