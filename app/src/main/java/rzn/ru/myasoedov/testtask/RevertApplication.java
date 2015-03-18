package rzn.ru.myasoedov.testtask;

import android.app.Application;
import android.util.LruCache;

/**
 * Created by grisha on 18.03.15.
 */
public class RevertApplication extends Application {
    public static final int CACHE_SIZE = 1024 * 1024;
    private static LruCache<String, String> cache;

    @Override
    public void onCreate() {
        super.onCreate();
        cache = new LruCache<>(CACHE_SIZE);
    }

    public static LruCache<String, String> getCache() {
        return cache;
    }

}
