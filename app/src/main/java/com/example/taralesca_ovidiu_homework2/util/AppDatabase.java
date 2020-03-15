package com.example.taralesca_ovidiu_homework2.util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.taralesca_ovidiu_homework2.dao.UserDao;
import com.example.taralesca_ovidiu_homework2.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "db";
    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, DB_NAME).build();
    }

    public abstract UserDao userDao();
}
