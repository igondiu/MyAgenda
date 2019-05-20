package com.example.myagenda.databaseClasses;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class, Agenda_Class.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {

    public abstract BDDAccessInterface  appDataBaseObject();
}
