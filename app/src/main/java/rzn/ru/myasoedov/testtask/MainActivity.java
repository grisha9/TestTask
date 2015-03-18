package rzn.ru.myasoedov.testtask;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<String> {
    public static final int LOADER_TEXT_REVERT_ID = 100;
    private EditText text;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.text);
        result = (TextView) findViewById(R.id.result_text);
        getLoaderManager().initLoader(LOADER_TEXT_REVERT_ID, null, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_revert) {
            Bundle bundle = new Bundle();
            bundle.putString(TextRevertLoader.TEXT, text.getText().toString());
            getLoaderManager().restartLoader(LOADER_TEXT_REVERT_ID, bundle, this);
            return true;
        } else if (id == R.id.action_clear) {
            text.setText("");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle bundle) {
        Loader<String> loader = null;
        if (id == LOADER_TEXT_REVERT_ID) {
            loader = new TextRevertLoader(this, bundle);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        result.setText(data);
    }


    @Override
    public void onLoaderReset(Loader<String> listLoader) {

    }

}
