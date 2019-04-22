package com.example.myagenda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.Serializable;

public class Account_Fragment extends Fragment {

    private Button LogOutBT ;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  LaVue;
        LaVue = inflater.inflate(R.layout.fragment_account, container, false);

        LogOutBT = LaVue.findViewById(R.id.Log_out);

        LogOutBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GoMain = new Intent(getActivity(), Login_Acti.class);
                GoMain.putExtra("logged", false);
                startActivity(GoMain);
            }
        });
        return LaVue;
    }



    
}
