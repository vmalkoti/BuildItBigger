package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.Toast;

import com.example.jokeactivitylibrary.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokesApi.JokesApi;

import java.io.IOException;
import java.lang.ref.WeakReference;

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String LOG_TAG = "DEBUG_" + EndpointsAsyncTask.class.getSimpleName();
    public static final String ACTION_JOKE_BROADCAST = "ACTION_JOKE_BROADCAST";
    private static JokesApi jokesApiService = null;
    private WeakReference<Context> contextRef;

    EndpointsAsyncTask(Context context) {
        contextRef = new WeakReference<>(context);
    }

    @Override
    protected String doInBackground(Void[] voids) {
        if(jokesApiService == null) {
            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            jokesApiService = builder.build();
        }

        try {
            return jokesApiService.getJoke().execute().getJoke();
        } catch (IOException e) {
            Log.d(LOG_TAG, e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Context context = contextRef.get();

        if(context != null) {
            // send a local broadcast if activity context is still valid
            LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
            Intent broadcastIntent = new Intent(ACTION_JOKE_BROADCAST);
            broadcastIntent.putExtra(Intent.EXTRA_TEXT, result);
            manager.sendBroadcast(broadcastIntent);
        }
    }
}
