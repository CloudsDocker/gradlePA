/**
 * Created by todzhang on 2017/1/2.
 */
public class oSingleton {
    private static oSingleton ourInstance = new oSingleton();

    public static oSingleton getInstance() {
        return ourInstance;
    }

    private oSingleton() {
    }
}
