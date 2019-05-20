package com.example.myagenda.databaseClasses;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BDDAccessInterface {

    // Working with the user info  BEGIN

    @Insert
    public void addUser(User user);

    @Query("Select * from user")
    public List<User> readUser();

    @Update
    public void updateUser(User user);

    @Delete
    public void deleteUser(User user);

    // Working with user info END

    // working with agenda Tasks BEGIN :

    @Insert
    public void addTask(Agenda_Class agenda);

    @Query("select * from agenda")
    public List<Agenda_Class> readTasks();

    @Update
    public void updateTask(Agenda_Class agenda);

    @Delete
    public void deleteTask(Agenda_Class agenda);
    // Working with agenda Tasks END
}
