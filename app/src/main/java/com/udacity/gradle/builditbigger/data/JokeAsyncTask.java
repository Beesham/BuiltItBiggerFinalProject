package com.udacity.gradle.builditbigger.data;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;


/**
 * Created by beesham on 08/12/16.
 */

public class JokeAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static JokeApi jokeApiService = null;
    private Context context;
    public AsyncResponse delegate = null;

    public interface AsyncResponse{
        void jokeResponse(String joke);
    }

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {
        if(jokeApiService == null){
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://192.168.0.22:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            jokeApiService = builder.build();
        }

        context = pairs[0].first;

        try{
            return jokeApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        delegate.jokeResponse(s);
    }
}
