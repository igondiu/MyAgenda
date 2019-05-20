package com.example.myagenda;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myagenda.databaseClasses.AppDataBase;
import com.example.myagenda.databaseClasses.ConnectionJson;
import com.example.myagenda.databaseClasses.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Login_Acti extends AppCompatActivity {

    private TextView Nombre_Essais;
    private Button Connect_BT;
    private EditText Login;
    private EditText Password;
    private int Compteur = 5;
    private Button RegisterBT;
    private SharedPreferences sp;
    private static final String LoginURL = "http://android.ega.tf/gr4/admin/login_register/login.php";
    private static final String URLInfo = "http://android.ega.tf/gr4/product/";
    boolean RequestReturn = false;
    public static AppDataBase appDataBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        appDataBase = Room.databaseBuilder(this, AppDataBase.class, "userinfo").allowMainThreadQueries().build();


        Login = findViewById(R.id.LoginAREA);
        Connect_BT = findViewById(R.id.CONNECT_button);
        RegisterBT = findViewById(R.id.Register_BT);
        Password = findViewById(R.id.PasswordAREA);
        Nombre_Essais = findViewById(R.id.attempts_nb);
        sp = getSharedPreferences("login", MODE_PRIVATE);


        // Log Out button clicked
        if(!(getIntent().getBooleanExtra("logged",true))){
            sp.edit().putBoolean("logged", false).commit();
        }


        if(sp.getBoolean("logged",false)){
            Intent intent = new Intent(Login_Acti.this, MainActivity.class);
            startActivity(intent);
        }
        Nombre_Essais.setText(getString(R.string.Attempts_Remaining)+String.valueOf(Compteur));

        Connect_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!CredentialsValidation(Login.getText().toString().trim(), Password.getText().toString().trim()))
                {
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

            }
        });



    }

    public boolean CredentialsValidation(final String LoginVR, final String PwdVR)
    {
        Log.i("Login Transmis : ", LoginVR);
        Log.i("MDP Transmis : ", PwdVR);
        StringRequest Request = new StringRequest(StringRequest.Method.POST, LoginURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Retour MYSQL ", "Un retour est reçu");
                if(!response.contains("error")){
                    Log.i("IF de ONreSPONSE", response);
                    RequestReturn = true;

                    Intent intent = new Intent(Login_Acti.this, MainActivity.class);
                    startActivity(intent);
                    sp.edit().putBoolean("logged", true).commit();
                    try {
                        List<User> user = new ConnectionJson(response).execute(URLInfo).get();
                        Log.i("Info from JSON : ", user.get(0).getEmail());
                        appDataBase.appDataBaseObject().addUser(user.get(0));
                        Toast.makeText(getApplicationContext(),"Welcome Back "+user.get(0).getPrenom()+" !", Toast.LENGTH_LONG).show();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }else{
                    Toast.makeText(getApplicationContext(),"Invalid Username or Password", Toast.LENGTH_LONG).show();
                    Log.i("ELSE de ONreSPONSE", response);
                    RequestReturn =  false;
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("EROOOOOOORRRR Volley", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =  new HashMap<>();
                params.put("loginuser","true");
                params.put("username", LoginVR);
                params.put("userpassword", PwdVR);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(Request);

        return RequestReturn;
    }
}
