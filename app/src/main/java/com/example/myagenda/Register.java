package com.example.myagenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

   private EditText varNom ;
   private EditText varPrenom;
   private EditText varEmail ;
   private EditText varPhone;
   private Button RegButton_OBJ ;
   private View laVue;

   private  static  final String urlReg = "http://android.ega.tf/gr4/admin/login_register/registeruser.php";
   private static final String urlPsw = "http://android.ega.tf/gr4/admin/login_register/getUserPassword.php";


    String TempMessageString;
    boolean wasError;
    boolean isInserted =false;
    RequestQueue queue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        varNom = findViewById(R.id.Reg_Nom_UI);
        varPrenom = findViewById(R.id.Reg_Prenom_UI);
        varEmail = findViewById(R.id.Reg_Email_UI);
        varPhone = findViewById(R.id.Reg_Phone_UI);
        RegButton_OBJ = findViewById(R.id.Reg_Button_UI);
        wasError = false;
        queue =  Volley.newRequestQueue(this);
        laVue = findViewById(R.id.Register_BT);



        RegButton_OBJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(varNom.getText().toString().isEmpty()){
                    TempMessageString = "Le Nom ne peut pas être vide";
                    showAlert(v,varNom);
                    wasError=true;
                }else{
                    wasError=false;
                }

                if(varPrenom.getText().toString().isEmpty()){
                    TempMessageString = "Le Prénom ne peut pas être vide";
                    showAlert(v,varPrenom);
                    wasError=true;
                }else{
                    wasError=false;
                }

                if(varEmail.getText().toString().isEmpty()){
                    TempMessageString = "L'email ne peut pas être vide";
                    showAlert(v,varEmail);
                    wasError=true;
                }else{
                    wasError=false;
                }

                if(varPhone.getText().toString().isEmpty()){
                    TempMessageString = "Le numéro ne peut pas être vide";
                    showAlert(v,varPhone);
                    wasError=true;
                }else{
                    wasError=false;
                }
                Log.i("La valeur de wasEror : ", "Valeur "+wasError);
                if(wasError==false){
                    InsertintoMySQL();
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

    public void showPassword(View view, final String psw){
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage(psw)
                .setPositiveButton("J'ai bien noté mon mot de passe", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent( Register.this, Login_Acti.class);
                        startActivity(intent);
                    }
                })
                .setTitle("Voici votre mot de passe :")
                .create();
        myAlert.show();
    }

    public Boolean InsertintoMySQL(){
            StringRequest request = new StringRequest(Request.Method.POST, urlReg, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.contains("success")){
                        isInserted  = true;
                        Toast.makeText(getApplicationContext(),"Bienvenue "+varPrenom.getText().toString(), Toast.LENGTH_LONG).show();
                        getPassword(varEmail.getText().toString());
                    }else{
                        Toast.makeText(getApplicationContext(),"An error occured", Toast.LENGTH_LONG).show();
                        isInserted = false;
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"An error occured", Toast.LENGTH_LONG).show();
                    Log.e("The errror is  : ", error.toString());
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String , String> params = new HashMap<>();
                    params.put("registeruser","true");
                    params.put("usernom",varNom.getText().toString());
                    params.put("userprenom", varPrenom.getText().toString());
                    params.put("usercontact", varPhone.getText().toString());
                    params.put("useremail", varEmail.getText().toString());
                    return  params;
                }
            };
            queue.add(request);
            return isInserted;
    }

    public void getPassword(final String loginDB){

        StringRequest request = new StringRequest(Request.Method.POST, urlPsw, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    showPassword(laVue,response);
                }else{
                    Toast.makeText(getApplicationContext(),"An error occured", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"An error occured", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("getpsw","true");
                params.put("username",loginDB);
                return  params;
            }
        };
        queue.add(request);
    }
}
