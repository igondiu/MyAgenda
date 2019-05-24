package com.example.myagenda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myagenda.databaseClasses.Agenda_Class;
import com.example.myagenda.databaseClasses.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Account_Fragment extends Fragment {

    private Button LogOutBT ;
    private TextView TextUser;
    List<User> list = new ArrayList<>();
    List<Agenda_Class> task = new ArrayList<>();
    View  LaVue;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LaVue = inflater.inflate(R.layout.fragment_account, container, false);

        LogOutBT = LaVue.findViewById(R.id.Log_out);
        TextUser = LaVue.findViewById(R.id.userText);
        TextUser.setText("Hey ");


        return LaVue;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list= Login_Acti.appDataBase.appDataBaseObject().readUser();
        if(list.size()!=0){
            TextUser.setText("Hey "+list.get(0).getPrenom());
        }else {
            LogOutBT.performClick();
        }
        task = MainActivity.appDataBase.appDataBaseObject().readTasks();
        for (int i=0;i<list.size();i++){
            Log.v("Les USERS dans La BDD ", list.get(i).getId()+" "+list.get(i).getNom()+" "+list.get(i).getPrenom()+"\n");
        }

        for (int i=0;i<task.size();i++){
            Log.v("LES TASKS dans la BDD ", task.get(i).getTitre()+" "+task.get(i).getDescription()+"\n");
        }

        LogOutBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GoMain = new Intent(getActivity(), Login_Acti.class);
                GoMain.putExtra("logged", false);
                for(int i=0; i<list.size(); i++){
                    Login_Acti.appDataBase.appDataBaseObject().deleteUser(list.get(i));
                }
                if(task.size() != 0){
                    for(int i=0; i<task.size(); i++){
                        MainActivity.appDataBase.appDataBaseObject().deleteTask(task.get(i));
                    }
                }
                startActivity(GoMain);
                for (int i=0;i<list.size();i++){
                    Log.v("Les USr dans La BDD LG ", list.get(i).getId()+" "+list.get(i).getNom()+" "+list.get(i).getPrenom()+"\n");
                }
            }
        });

    }
}
