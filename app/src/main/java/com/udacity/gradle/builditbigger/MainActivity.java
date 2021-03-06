package com.udacity.gradle.builditbigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jokeactivitylibrary.JokeActivity;


public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String joke = intent.getStringExtra(Intent.EXTRA_TEXT);

            if(joke !=null) {
                context.startActivity(MainActivity.getJokeActivityLaunchIntent(context, joke));
            } else {
                Toast.makeText(MainActivity.this, "Could not retrieve joke", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(broadcastReceiver,
                        new IntentFilter(EndpointsAsyncTask.ACTION_JOKE_BROADCAST));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(broadcastReceiver);
    }

    public void tellJoke(View view) {
        new EndpointsAsyncTask(this).execute();
    }


    public static Intent getJokeActivityLaunchIntent(Context context, String joke) {
        Intent activityIntent = new Intent(context, JokeActivity.class);
        activityIntent.putExtra(Intent.EXTRA_TEXT, joke);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return  activityIntent;
    }

}
