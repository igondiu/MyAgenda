package com.example.myagenda;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class Agenda_Fragment extends Fragment {

    CalendarView calendrier;
    View v;
    Dialog onCalendarViewDialog;
    String dateClicked;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_agenda, container, false);

        calendrier = v.findViewById(R.id.calendarView2);

        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onCalendarViewDialog = new Dialog(getActivity());
        onCalendarViewDialog.setContentView(R.layout.activity_tache);
        onCalendarViewDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        calendrier.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                TextView dateChoisie = onCalendarViewDialog.findViewById(R.id.dateToShow);
                dateClicked = dayOfMonth+"/"+month+"/"+year;
                Log.i("La date choisie est :", dateClicked);
                dateChoisie.setText(dateClicked);
                onCalendarViewDialog.show();

            }
        });
    }
}
