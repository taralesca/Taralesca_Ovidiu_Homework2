package com.example.taralesca_ovidiu_homework2.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taralesca_ovidiu_homework2.R;
import com.example.taralesca_ovidiu_homework2.model.User;
import com.example.taralesca_ovidiu_homework2.util.DeleteUser;
import com.example.taralesca_ovidiu_homework2.util.GetAllUsers;
import com.example.taralesca_ovidiu_homework2.util.InsertUser;
import com.example.taralesca_ovidiu_homework2.util.RVAdapter;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private RVAdapter mAdapter = new RVAdapter(new String[]{});

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        updateRecyclerView();

        final EditText nameField = findViewById(R.id.name_field);
        final EditText markField = findViewById(R.id.mark_field);
        final Button addUser = findViewById(R.id.add_user);
        final Button removeUser = findViewById(R.id.remove_user);

        addUser.setOnClickListener(v -> performAddUserButtonAction(nameField, markField));
        removeUser.setOnClickListener(v -> performRemoveUserButtonAction(nameField));

        recyclerView.setAdapter(mAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void performRemoveUserButtonAction(EditText nameField) {
        final String[] names = nameField.getText().toString().split(" ");

        if (names.length != 2) {
            displayToast(getString(R.string.invalid_name));
        } else {
            try {
                final String firstName = names[0];
                final String lastName = names[1];
                if (!attemptUserDelete(firstName, lastName)) {
                    displayToast(getString(R.string.user_not_found));
                }
            } catch (ExecutionException | InterruptedException e) {
                displayToast(getString(R.string.internal_error));
            }
            updateRecyclerView();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void performAddUserButtonAction(EditText nameField, EditText markField) {
        final String namesField = nameField.getText().toString();

        final Integer mark;
        try {
            mark = Integer.valueOf(markField.getText().toString());
            if (mark > 10 || mark < 1) {
                throw new NumberFormatException(getString(R.string.invalid_mark));
            }
        } catch (NumberFormatException e) {
            displayToast(getString(R.string.invalid_mark));
            return;
        }


        if (!namesField.isEmpty()) {
            final String[] names = namesField.split(" ");
            if (names.length != 2) {
                displayToast(getString(R.string.invalid_inputs));
                return;
            }
            final String firstName = names[0];
            final String lastName = names[1];
            new InsertUser(getApplicationContext(), new User(firstName, lastName, mark)).execute();
            updateRecyclerView();
        }
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private Boolean attemptUserDelete(String firstName, String lastName)
            throws ExecutionException, InterruptedException {

        return new DeleteUser(getApplicationContext(), firstName, lastName).execute().get();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateRecyclerView() {
        try {
            final List<User> users = new GetAllUsers(getApplicationContext()).execute().get();
            mAdapter.swapDataSet(
                    users.stream()
                            .map(user -> user.getFirstName()
                                    + " " + user.getLastName()
                                    + ": " + user.getMark())
                            .toArray(String[]::new)
            );

        } catch (ExecutionException | InterruptedException e) {
            displayToast(getString(R.string.user_list_problem));
        }
    }

}
