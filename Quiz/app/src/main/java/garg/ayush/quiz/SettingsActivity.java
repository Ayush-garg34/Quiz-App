package garg.ayush.quiz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {

    ArrayList<Points> pointsArrayList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pointsArrayList = new ArrayList<>();

            setContentView(R.layout.activity_settings);
            recyclerView = findViewById(R.id.rv);

            final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            final DatabaseReference rootRef = firebaseDatabase.getReference();

            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

            final MyAdapter myAdapter = new MyAdapter(getBaseContext(), pointsArrayList);
            recyclerView.setAdapter(myAdapter);
            rootRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();

//                    Log.e("TAG", "onDataChange: " );
//                    HashMap hashMap = new HashMap<String,Integer>();
                    for (DataSnapshot ds : dataSnapshots) {

                        int i = Integer.parseInt(ds.child("points").getValue().toString());
//                        Log.d("Ans",ds.toString()+"");

//                        hashMap.put(ds.child("email").getValue().toString(),ds.child("points").getValue());
                        pointsArrayList.add(new Points(ds.child("email").getValue().toString(), i));
                        Collections.sort(pointsArrayList);
//                        Log.e("TAG", "onDataChange: " + ds.getValue().toString());
                    }

//                    Collections.sort(pointsArrayList);
//                    Log.e("TAG", "onDataChange: " +dataSnapshots);
                    myAdapter.notifyDataSetChanged();
                    pointsArrayList = new ArrayList<>();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });


        }

    }
