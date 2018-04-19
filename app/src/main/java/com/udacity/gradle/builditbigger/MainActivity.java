package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import appkite.jordiguzman.com.activitylibjokes.ActivityJoke;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.label));
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

    public void tellJokeFromMain(View view) {
       new EndpointsAsyncTask(this, progressBar).execute();
    }

    public static class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {




        private final AtomicReference<Context> mContext = new AtomicReference<>();

        private final AtomicReference<ProgressBar> progressBar = new AtomicReference<>();



        EndpointsAsyncTask(Context context, ProgressBar progressBar){
            this.mContext.set(context);
            this.progressBar.set(progressBar);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.get().setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            MyApi myApi = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    }).build();
            try {
                return myApi.tellJokes().execute().getData();
            }catch (IOException e){
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.get().setVisibility(View.INVISIBLE);
            ActivityJoke.tellJokes(mContext.get(), s);
        }

    }


}
