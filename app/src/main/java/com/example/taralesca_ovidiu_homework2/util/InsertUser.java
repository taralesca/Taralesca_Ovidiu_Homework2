package com.example.taralesca_ovidiu_homework2.util;

import android.content.Context;
import android.os.AsyncTask;

import com.example.taralesca_ovidiu_homework2.model.User;

public class InsertUser extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private User user;

    public InsertUser(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        AppDatabase.getInstance(this.context).userDao().insertAll(user);
        return true;
    }
}
