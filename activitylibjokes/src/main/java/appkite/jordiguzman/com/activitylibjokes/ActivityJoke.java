package appkite.jordiguzman.com.activitylibjokes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ActivityJoke extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        setTitle("");

        TextView tv_joke = findViewById(R.id.tv_joke);
        String joke = getIntent().getStringExtra("joke");

        tv_joke.setText(joke);
    }

    public static void tellJokes(Context context, String joke){
        Intent intent = new Intent(context, ActivityJoke.class);
        intent.putExtra("joke", joke);
        context.startActivity(intent);
    }
}
