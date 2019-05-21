package com.example.myagenda;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myagenda.databaseClasses.Agenda_Class;
import com.example.myagenda.databaseClasses.AppDataBase;

public class Add_Fragment extends Fragment {

    private EditText chTitre;
    private EditText chDescription;
    private EditText chDebut;
    private EditText chFin;
    private EditText chLieu;
    private Button btSave;
    private Button btRecurrence;
    public static AppDataBase appDataBase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  LaVue;
        LaVue = inflater.inflate(R.layout.fragment_add, container, false);

        chTitre = LaVue.findViewById(R.id.Titre);
        chDescription = LaVue.findViewById(R.id.Description);
        chDebut = LaVue.findViewById(R.id.Debut);
        chFin = LaVue.findViewById(R.id.Fin);
        chLieu = LaVue.findViewById(R.id.Place);
        btRecurrence = LaVue.findViewById(R.id.Reccurence);
        btSave = LaVue.findViewById(R.id.Save);
        appDataBase = Room.databaseBuilder(getActivity(), AppDataBase.class, "Agenda").allowMainThreadQueries().build();


        return LaVue;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btRecurrence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chDescription.getText().toString().isEmpty() || chTitre.getText().toString().isEmpty()){
                    showAlert(v);
                }else {
                    Agenda_Class task = new Agenda_Class();
                    task.setDate_creation("");
                    task.setDate_debut(chDebut.getText().toString());
                    task.setDate_fin(chFin.getText().toString());
                    task.setId_user(Login_Acti.appDataBase.appDataBaseObject().readUser().get(0).getId());
                    task.setLieu(chLieu.getText().toString());

                    appDataBase.appDataBaseObject().addTask(task);
                    //Log.i("Titre from ROOM : ", appDataBase.appDataBaseObject().readTasks().get(0).getTitre());
                    Toast.makeText(getActivity(),"Bien enregistré ! ", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    public void showAlert(View view){
        AlertDialog.Builder myAlert = new AlertDialog.Builder(getActivity());
        myAlert.setMessage("Au moins un Titre et/ou une description doit être renseignée")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("Attention !")
                .create();
        myAlert.show();
    }
}
