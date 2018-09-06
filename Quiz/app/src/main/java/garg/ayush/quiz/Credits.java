package garg.ayush.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        ImageView imageView = findViewById(R.id.image);
        Picasso.get().load("https://www.iitgn.ac.in/student/lifeoncampus/clubs/images/metis.jpg").into(imageView);
    }
}
