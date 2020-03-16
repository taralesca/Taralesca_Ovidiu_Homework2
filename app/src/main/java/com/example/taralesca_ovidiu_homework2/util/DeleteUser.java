package com.example.taralesca_ovidiu_homework2.util;

import android.content.Context;
import android.os.AsyncTask;

import com.example.taralesca_ovidiu_homework2.model.User;

public class DeleteUser extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private String firstName;
    private String lastName;

    public DeleteUser(Context context, String firstName, String lastName) {
        this.context = context;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        final User user = AppDatabase.getInstance(this.context).userDao().findByName(firstName, lastName);
        if (user == null) {
            return false;
        }
        AppDatabase.getInstance(this.context).userDao().delete(user);
        return true;
    }
}
