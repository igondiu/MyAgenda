package com.example.myagenda;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

   private EditText varNom ;
   private EditText varPrenom;
   private EditText varEmail ;
   private EditText varPhone;
   private Button RegButton_OBJ ;


    String TempMessageString;
    int wasError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        varNom = findViewById(R.id.Reg_Nom_UI);
        varPrenom = findViewById(R.id.Reg_Prenom_UI);
        varEmail = findViewById(R.id.Reg_Email_UI);
        varPhone = findViewById(R.id.Reg_Phone_UI);
        RegButton_OBJ = findViewById(R.id.Reg_Button_UI);
        wasError = 0;



        varNom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && (wasError==0)){

                    if(varNom.getText().toString().isEmpty()){
                        TempMessageString = "Le Nom ne peut pas être vide";
                        showAlert(v,varNom);
                        wasError=1;
                    }else{
                        wasError=0;
                    }
                }
            }
        });

        varPrenom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && (wasError==0)){

                    if(varPrenom.getText().toString().isEmpty()){
                        TempMessageString = "Le Prénom ne peut pas être vide";
                        showAlert(v,varPrenom);
                        wasError=1;
                    }else{
                        wasError=0;
                    }
                }
            }
        });

        varEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && (wasError==0)){

                    if(varEmail.getText().toString().isEmpty()){
                        TempMessageString = "L'email ne peut pas être vide";
                        showAlert(v,varEmail);
                        wasError=1;
                    }else{
                        wasError=0;
                    }
                }
            }
        });

        varPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && (wasError==0)){

                    if(varPhone.getText().toString().isEmpty()){
                        TempMessageString = "Le numéro ne peut pas être vide";
                        showAlert(v,varPhone);
                        wasError=1;
                    }else{
                        wasError=0;
                    }
                }
            }
        });


        RegButton_OBJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(varNom.getText().toString().isEmpty()){
                    TempMessageString = "Le Nom ne peut pas être vide";
                    showAlert(v,varNom);
                }
                if(varPrenom.getText().toString().isEmpty()){
                    TempMessageString = "Le Prénom ne peut pas être vide";
                    showAlert(v,varPrenom);
                }
                if(varEmail.getText().toString().isEmpty()){
                    TempMessageString = "L'email ne peut pas être vide";
                    showAlert(v,varEmail);
                }
                if(varPhone.getText().toString().isEmpty()){
                    TempMessageString = "Le numéro ne peut pas être vide";
                    showAlert(v,varPhone);
                }

            }
        });



    }

    public void showAlert(View view, final EditText object){
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage(TempMessageString)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        object.requestFocus();
                    }
                })
                .setTitle("Attention !")
                .create();
        myAlert.show();

    }
}
