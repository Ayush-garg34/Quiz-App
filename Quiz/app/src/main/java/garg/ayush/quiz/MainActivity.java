package garg.ayush.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity{

    int score = 0;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference rootRef = firebaseDatabase.getReference();

        Intent i = getIntent();

        name = i.getStringExtra("name");
        final CheckBox option1, option2, option3, option4;
        Button Submit;

        final Button next = findViewById(R.id.Q1Next);
        option1 = findViewById(R.id.Q1C1);
        option2 = findViewById(R.id.Q1C2);
        option3 = findViewById(R.id.Q1C3);
        option4 = findViewById(R.id.Q1C4);

        Submit = findViewById(R.id.Q1Submit);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (option1.isChecked()) {
                    option2.setChecked(false);
                    option3.setChecked(false);
                    option4.setChecked(false);
                }
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (option2.isChecked()) {
                    option1.setChecked(false);
                    option3.setChecked(false);
                    option4.setChecked(false);
                }
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (option3.isChecked()) {
                    option2.setChecked(false);
                    option1.setChecked(false);
                    option4.setChecked(false);
                }
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (option4.isChecked()) {
                    option2.setChecked(false);
                    option3.setChecked(false);
                    option1.setChecked(false);
                }
            }
        });

        next.setVisibility(View.GONE);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the correct option is marked true and add points

                if (option1.isChecked()) {
                    rootRef.child(name).setValue(new Points(name, score));
                }
                if (option2.isChecked()) {
                    rootRef.child(name).setValue(new Points(name, score));
                }
                if (option3.isChecked()) {
                    score = score + 10;
                    rootRef.child(name).setValue(new Points(name, score));
                }
                if (option4.isChecked())
                    rootRef.child(name).setValue(new Points(name, score));

                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                next.setVisibility(View.VISIBLE);
                option3.setTextColor(Color.rgb(51, 255, 51));
                option2.setTextColor(Color.rgb(255, 51, 51));
                option1.setTextColor(Color.rgb(255, 51, 51));
                option4.setTextColor(Color.rgb(255, 51, 51));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("name",name);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
                intent.putExtra("name",name);

                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
