package com.example.taralesca_ovidiu_homework2.activities;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taralesca_ovidiu_homework2.R;
import com.example.taralesca_ovidiu_homework2.model.User;
import com.example.taralesca_ovidiu_homework2.util.GetAllUsers;
import com.example.taralesca_ovidiu_homework2.util.MyAdapter;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        try {
            final List<User> users = new GetAllUsers(getApplicationContext()).execute().get();
            mAdapter = new MyAdapter((users.stream().map(user -> user.getFirstName() + user.getLastName()).toArray(String[]::new)));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(mAdapter);
    }

}
