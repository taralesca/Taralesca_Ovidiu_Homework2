package com.example.taralesca_ovidiu_homework2.util;

import android.content.Context;
import android.os.AsyncTask;

import com.example.taralesca_ovidiu_homework2.model.User;

import java.util.List;

public class GetAllUsers extends AsyncTask<Void, Void, List<User>> {
    private Context context;

    public GetAllUsers(Context context) {
        this.context = context;
    }

    @Override
    protected List<User> doInBackground(Void... voids) {
        return AppDatabase.getInstance(context).userDao().getAll();
    }
}
