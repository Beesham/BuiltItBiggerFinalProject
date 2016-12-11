package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;
import android.text.TextUtils;

import static junit.framework.Assert.*;

import com.example.JokeProvider;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils;
import com.udacity.gradle.builditbigger.data.JokeAsyncTask;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import builditbigger.MainActivity;

import static android.support.test.InstrumentationRegistry.getContext;

/**
 * Created by beesham on 10/12/16.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest implements JokeAsyncTask.AsyncResponse{

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAsyncTask(){
        JokeProvider jokeProvider = new JokeProvider();
        JokeAsyncTask jokeAsyncTask = new JokeAsyncTask();
        jokeAsyncTask.delegate = this;
        jokeAsyncTask.execute(new Pair<Context, String>(getContext(), jokeProvider.getJoke()));
    }

    @Override
    public void jokeResponse(String joke) {
        assertTrue(!TextUtils.isEmpty(joke));
    }
}
