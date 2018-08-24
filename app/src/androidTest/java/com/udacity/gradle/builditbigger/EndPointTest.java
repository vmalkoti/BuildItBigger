package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static android.support.test.InstrumentationRegistry.getContext;
import static junit.framework.Assert.assertNotNull;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EndPointTest  {
    private static final String LOG_TAG = "DEBUG_" + EndPointTest.class.getSimpleName();

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void navigationTest() {
        String result = null;

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();


        try {
            endpointsAsyncTask.execute(new Pair<>(getContext(), "Manuel"));
            result = endpointsAsyncTask.get();
            Log.d(LOG_TAG, "Joke retrieved in test : " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertNotNull(result);
    }

}
