package com.example.myagenda.databaseClasses;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "agenda")
public class Agenda_Class {

    @ColumnInfo(name = "id_user")
    private int id_user ;

    @PrimaryKey(autoGenerate = true)
    private int id_task;

    @ColumnInfo(name = "date_creation")
    private String date_creation;

    @ColumnInfo(name = "date_debut")
    private String date_debut;

    @ColumnInfo(name = "date_fin")
    private String date_fin;

    @ColumnInfo(name = "titre")
    private String titre;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "lieu")
    private String lieu;

    //Getters and Setters :

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
