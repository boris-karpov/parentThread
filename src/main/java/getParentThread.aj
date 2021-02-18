public aspect getParentThread {
    pointcut threadStarting():
    call(* java.lang.Thread+.start()) ||
                call(* ExecutorService+.execute(..)) ||
                call(* ExecutorService+.submit(..)) ||
                call(* ExecutorService+.invokeAll(..)) ||
                call(* ExecutorService+.invokeAny(..)) ||
                call(* ScheduledExecutorService.scheduleAtFixedRate(..));
    Object around(): threadStarting() {
    long parentThread = Thread.currentThread().getId();
    Object ret = proceed();
//    long newThread = Thread.currentThread().getId();
    System.out.println("Aspectj!!");
    return ret;
    }
    before() :
        if(!DEBUG) && (
            call(* Thread+.start()) ||
            call(* ExecutorService+.execute(..)) ||
            call(* ExecutorService+.submit(..)) ||
            call(* ExecutorService+.invokeAll(..)) ||
            call(* ExecutorService+.invokeAny(..)) ||
            call(* ScheduledExecutorService.scheduleAtFixedRate(..))
        )
    {
        System.out.println(thisJoinPoint);
    }

}
