package research.parentThread;

import java.util.Properties;

public class TestProperties {

    public static Properties storage = new Properties(); //общая база для всех тестовых потоков

    public static String getPrimaryThreadId(String s){
        String p = s;
        while (null != TestProperties.storage.getProperty(p)) {
            p = TestProperties.storage.getProperty(p);
        }
        return p;
    }

}
