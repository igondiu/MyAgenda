package com.example.myagenda;


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
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.myagenda.MainActivity.task;

import static com.example.myagenda.MainActivity.appDataBase;

public class Add_Fragment extends Fragment {

    private EditText chTitre;
    private EditText chDescription;
    private EditText chDebut;
    private EditText chFin;
    private EditText chLieu;
    private Button btSave;
    private Button btRecurrence;
    private View  LaVue;
    private boolean isEditMod = false;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LaVue = inflater.inflate(R.layout.fragment_add, container, false);
        chTitre = LaVue.findViewById(R.id.Titre);
        chDescription = LaVue.findViewById(R.id.Description);
        chDebut = LaVue.findViewById(R.id.Debut);
        chFin = LaVue.findViewById(R.id.Fin);
        chLieu = LaVue.findViewById(R.id.Place);
        btRecurrence = LaVue.findViewById(R.id.Reccurence);
        btSave = LaVue.findViewById(R.id.Save);


        return LaVue;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String jsonAgenda = getActivity().getIntent().getStringExtra("myEvent");
        if(jsonAgenda != null && !jsonAgenda.isEmpty()) {
            isEditMod = true;
            Agenda_Class myAgenda = new Agenda_Class();
            try {
                myAgenda = new Agenda_Class(new JSONObject(jsonAgenda));
                chDebut.setText(myAgenda.getDate_debut());
                chDescription.setText(myAgenda.getDescription());
                chFin.setText(myAgenda.getDate_fin());
                chLieu.setText(myAgenda.getLieu());
                chTitre.setText(myAgenda.getTitre());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

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
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                    String currentDateandTime = sdf.format(new Date());
                    Log.i("La date et l'heure : ", currentDateandTime);
                    task.setDate_creation(currentDateandTime);
                    task.setDate_debut(chDebut.getText().toString());
                    task.setDate_fin(chFin.getText().toString());
                    task.setId_user(Login_Acti.appDataBase.appDataBaseObject().readUser().get(0).getId());
                    task.setLieu(chLieu.getText().toString());
                    task.setDescription(chDescription.getText().toString());
                    task.setTitre(chTitre.getText().toString());
                    appDataBase.appDataBaseObject().addTask(task);
                    Toast.makeText(getActivity(),"Bien enregistré ! ", Toast.LENGTH_LONG).show();
                    clearForm((ViewGroup )LaVue);
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
    private void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }
}
