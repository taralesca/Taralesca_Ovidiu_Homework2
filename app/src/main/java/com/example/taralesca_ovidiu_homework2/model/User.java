package com.example.taralesca_ovidiu_homework2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "mark")
    public Integer mark;

    public User(String firstName, String lastName, Integer mark) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mark = mark;
    }

    public Integer getMark() {
        return mark;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getUid() {
        return uid;
    }
}
