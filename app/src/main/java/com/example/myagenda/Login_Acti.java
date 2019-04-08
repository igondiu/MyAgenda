package com.example.myagenda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_Acti extends AppCompatActivity {

    private TextView Nombre_Essais;
    private Button Connect_BT;
    private EditText Login;
    private EditText Password;
    private int Compteur = 5;
    private Button RegisterBT;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login = findViewById(R.id.LoginAREA);
        Connect_BT = findViewById(R.id.CONNECT_button);
        RegisterBT = findViewById(R.id.Register_BT);
        Password = findViewById(R.id.PasswordAREA);
        Nombre_Essais = findViewById(R.id.attempts_nb);
        sp = getSharedPreferences("Login", MODE_PRIVATE);

        if(sp.getBoolean("logged",false)){
            Intent intent = new Intent(Login_Acti.this, MainActivity.class);
            startActivity(intent);
        }
        Nombre_Essais.setText(getString(R.string.Attempts_Remaining)+String.valueOf(Compteur));

        Connect_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CredentialsValidation(Login.getText().toString(), Password.getText().toString()))
                {
                    Intent intent = new Intent(Login_Acti.this, MainActivity.class);
                    startActivity(intent);
                    sp.edit().putBoolean("logged", true);
                }else{
                    Compteur--;
                    Nombre_Essais.setText(getString(R.string.Attempts_Remaining)+String.valueOf(Compteur));
                    if(Compteur == 0)
                    {
                        Connect_BT.setEnabled(false);
                    }
                }

            }
        });

        RegisterBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Login_Acti.this, Register.class );
                startActivity(intentReg);
                Log.i("Sort de Log", "Bien appelé le REGISTER");
            }
        });



    }

    public boolean CredentialsValidation(String LoginVR, String PwdVR)
    {

        return true;
    }
}
