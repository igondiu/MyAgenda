package com.example.myagenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "User_Agenda";
    private static final String Col1 = "id";
    private static final String Col2 = "date_creation";
    private static final String Col3 = "date_debut_evt";
    private static final String Col4 = "date_fin_evt";
    private static final String Col5 = "titre";
    private static final String Col6 = "description";
    private static final String Col7 = "lieu_evenement";
    private static final String Col8 = "ut_fut1_int";
    private static final String Col9 = "ut_fut2_varchar";
    private static final String Col10 = "ut_fut6_mediumtext";


    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    public  boolean addDataAgenda(int id, String date_cr, String date_deb, String date_fin, String titre, String descr,
                            String lieu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col1, id );
        contentValues.put(Col2, date_cr);
        contentValues.put(Col3, date_deb);
        contentValues.put(Col4, date_fin);
        contentValues.put(Col5, titre);
        contentValues.put(Col6, descr);
        contentValues.put(Col7, lieu);

        Log.d(TAG,"addData : Adding "+Col5+"to "+TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public  boolean addDataUserInfo(int id, String nom, String prenom, String sexe, String email, String numero){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d(TAG,"addData : Adding "+Col5+"to "+TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table "+TABLE_NAME+" ("+Col1+" int, "+Col2+" datetime, "+Col3+" datetime, "+Col4+" datetime, "
                +Col5+" varchar(50), "+Col6+" text, "+Col7
                +" varchar(50), "+Col8+" int(10), "+Col9+" varchar(50), "+Col10+" mediumtext )";
        db.execSQL(createTable);
    }

}
