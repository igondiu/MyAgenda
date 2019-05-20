package com.example.myagenda.databaseClasses;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.util.Log;

import org.json.JSONObject;

@Entity(tableName = "user")
public class User {
    @PrimaryKey()
    private int id;

    @ColumnInfo(name = "nom")
    private String nom;

    @ColumnInfo(name = "prenom")
    private String prenom;

    @ColumnInfo(name = "sexe")
    private String sexe;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "numetel")
    private String numtel;

    @ColumnInfo(name = "dateReg")
    private String dateReg;

    public User(JSONObject json){
        setId(json.optInt("id"));
        setNom(json.optString("nom"));
        setPrenom(json.optString("prenom"));
        setSexe(json.optString("sexe"));
        setEmail(json.optString("email"));
        setNumtel(json.optString("num_tel"));
        Log.i("Object Instantion ID : ", json.optString("nom"));
        Log.i("Object Intion Prenom : ",json.optString("prenom"));
    }

    public User(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getDateReg() {
        return dateReg;
    }

    public void setDateReg(String dateReg) {
        this.dateReg = dateReg;
    }
}
