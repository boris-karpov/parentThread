import research.parentThread.TestProperties;
public aspect getParentThread {
    pointcut threadStarting(): call(public void start());
    Object around(): threadStarting() {
    long parentThread = Thread.currentThread().getId();
    Object ret = proceed();
//    long newThread = Thread.currentThread().getId();
    TestProperties.storage.setProperty("parentThread", "parentThread");
    return ret;
    }
}
