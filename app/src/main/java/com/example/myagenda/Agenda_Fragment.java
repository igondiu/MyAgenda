package com.example.myagenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

public class Agenda_Fragment extends Fragment {

    CalendarView calendrier;
    View v;
    @Nullable


    @Override




    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v= inflater.inflate(R.layout.fragment_agenda, container, false);
        calendrier = v.findViewById(R.id.calendarView2);

        /*calendrier.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@androidx.annotation.NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //Intent intent = new Intent()
                  //      int
            }
        });*/


        return v;

    }

}
