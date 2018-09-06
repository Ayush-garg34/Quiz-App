package garg.ayush.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class Main3Activity extends AppCompatActivity{


    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setContentView(R.layout.activity_main3);
        final CheckBox option1, option2, option3, option4;
        option1 = findViewById(R.id.Q3C1);
        option2 = findViewById(R.id.Q3C2);
        option3 = findViewById(R.id.Q3C3);
        option4 = findViewById(R.id.Q3C4);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference rootRef = firebaseDatabase.getReference();

        Button Submit;

        Intent i = getIntent();

        final String name = i.getStringExtra("name");
        score = i.getIntExtra("score", 0);
        final Button next = findViewById(R.id.Q3Next);

        next.setVisibility(View.GONE);
        Submit = findViewById(R.id.Q3Submit);

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

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the correct option is marked true and add points

                if (option1.isChecked())
                    rootRef.child(name).setValue(new Points(name, (score)));
                if (option2.isChecked())
                    rootRef.child(name).setValue(new Points(name, (score)));
                if (option3.isChecked()) {
                    score += 10;
                    rootRef.child(name).setValue(new Points(name, (score)));
                    if (option4.isChecked()) {
                        rootRef.child(name).setValue(new Points(name, (score)));

                    }
                    option1.setClickable(false);
                    option2.setClickable(false);
                    option3.setClickable(false);
                    option4.setClickable(false);
                    next.setVisibility(View.VISIBLE);
                    option3.setTextColor(Color.rgb(51, 255, 51));
                    option2.setTextColor(Color.rgb(255, 51, 51));
                    option4.setTextColor(Color.rgb(255, 51, 51));
                    option1.setTextColor(Color.rgb(255, 51, 51));
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Main4Activity.class);
                intent.putExtra("score", score);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
}
