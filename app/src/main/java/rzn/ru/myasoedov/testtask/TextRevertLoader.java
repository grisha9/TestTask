package rzn.ru.myasoedov.testtask;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.LruCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by grisha on 18.03.15.
 */
public class TextRevertLoader extends AsyncTaskLoader<String> {
    public static final String WORD_DELIMITER = " ";
    public static final String TEXT = "text";
    private String text;

    public TextRevertLoader(Context context, Bundle bundle) {
        super(context);
        if (bundle != null) {
            text = bundle.getString(TEXT);
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        if (TextUtils.isEmpty(text)) {
            return "";
        }

        LruCache<String, String> cache = RevertApplication.getCache();
        String data = cache.get(text);

        if (TextUtils.isEmpty(data)) {
            String[] words = text.split(WORD_DELIMITER);
            List<String> result = new ArrayList<String>(Arrays.asList(words));
            Collections.reverse(result);
            String revertResult = TextUtils.join(WORD_DELIMITER, result);
            cache.put(text, revertResult);
            return revertResult;
        } else {
            return data;
        }
    }
}
