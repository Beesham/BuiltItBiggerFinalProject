package builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.JokeProvider;
import com.example.displayjokelibrary.JokeActivity;
import com.udacity.gradle.builditbigger.R;
import builditbigger.data.JokeAsyncTask;


public class MainActivity extends ActionBarActivity implements JokeAsyncTask.AsyncResponse{

    private static JokeProvider jokeProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jokeProvider = new JokeProvider();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        //Toast.makeText(this, jokeProvider.getJoke(), Toast.LENGTH_SHORT).show();
        /*Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra("joke", jokeProvider.getJoke());
        startActivity(intent);*/
        JokeAsyncTask jokeAsyncTask = new JokeAsyncTask();
        jokeAsyncTask.delegate = this;
        jokeAsyncTask.execute(new Pair<Context, String>(this, jokeProvider.getJoke()));
    }


    @Override
    public void jokeResponse(String joke) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra("joke", joke);
        startActivity(intent);
    }
}
