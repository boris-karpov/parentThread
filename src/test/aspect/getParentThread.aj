
import research.parentThread.TestProperties;

public aspect getParentThread {
    pointcut threadStarting(Thread t): call(public void start()) && target(t) ;
    Object around(Thread t): threadStarting(t) {
        long parentThreadId = Thread.currentThread().getId();
        Object ret = proceed(t);
        TestProperties.storage.setProperty(String.valueOf(t.getId()),String.valueOf(parentThreadId));
        System.out.println("Aspectj Thread Start Hook, around. ParentThreadId: [" + parentThreadId + "]"
        + " Target Id is: [" + t.getId() + "]");
        return ret;
    }
}
