package com.example.myagenda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myagenda.databaseClasses.Agenda_Class;
import com.example.myagenda.databaseClasses.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class Event_Fragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<Agenda_Class> tasks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v= inflater.inflate(R.layout.fragment_event, container, false);
         recyclerView = v.findViewById(R.id.RecyclerViewEvenet);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
         return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tasks = MainActivity.appDataBase.appDataBaseObject().readTasks();

    }
}
