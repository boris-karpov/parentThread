import java.util.concurrent.ExecutorService;
import research.parentThread.TestProperties;

public aspect getParentThread {
    pointcut threadStarting(Thread t): call(* java.lang.Thread+.start()) && target(t) ;
    Object around(Thread t): threadStarting(t) {
        long parentThreadId = Thread.currentThread().getId();
        Object ret = proceed(t);
//        System.out.println("Thread Class Name is: [" + t.getClass().getName() + "]");
//        TestProperties.storage.setProperty(String.valueOf(t.getId()),String.valueOf(parentThreadId));
        return ret;
    }
pointcut threadPooling(ExecutorService s, Runnable r): call(* submit(..)) && target(s) && args(r);
    Object around(ExecutorService s, Runnable r): threadPooling(s,r) {
        return proceed(s,r);
    }

}
