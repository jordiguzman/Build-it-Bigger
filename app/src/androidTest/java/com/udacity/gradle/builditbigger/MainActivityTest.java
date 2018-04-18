package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends InstrumentationTestCase{

    @Before
    public void setUp() throws Exception{
        super.setUp();
    }

    @After
    public void tearDown() throws Exception{
        super.tearDown();
    }

    @Test
    public void testAsyntask() throws Exception{
        final MainActivity.EndpointsAsyncTask jokeTask= new MainActivity.EndpointsAsyncTask(null);
        final String[] result = new String[1];
        final CountDownLatch signal = new CountDownLatch(1);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                result[0] = jokeTask.doInBackground();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                signal.countDown();
            }
        }.execute();
        signal.await(10, TimeUnit.SECONDS);
        String joke = result[0];
        assertTrue(joke != null && !joke.isEmpty());


    }


}
