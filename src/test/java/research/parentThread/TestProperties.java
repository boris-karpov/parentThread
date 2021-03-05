package research.parentThread;

public class TestProperties {

    public static String getParentThreadId(String s){
        return System.getProperty(s);
    }


    public static String getPrimaryThreadId(String s){
        String p = s;
        while (null != System.getProperty(System.getProperty(p))) {
            p = System.getProperty(p);
        }
        return p;
    }

}
