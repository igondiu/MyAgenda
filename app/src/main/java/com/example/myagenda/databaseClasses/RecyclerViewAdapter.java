package com.example.myagenda.databaseClasses;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.myagenda.Add_Fragment;

import com.example.myagenda.Agenda_Fragment;
import com.example.myagenda.R;
import com.google.gson.Gson;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Agenda_Class> mData;
    Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<Agenda_Class> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;

        v= LayoutInflater.from(mContext).inflate(R.layout.one_event, viewGroup, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        // Dialog ini

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_event);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.one_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialogTitre_tv = myDialog.findViewById(R.id.dialTitre);
                TextView dialogDesci_tv = myDialog.findViewById(R.id.dialDescri);
                TextView dialogLieu_tv = myDialog.findViewById(R.id.dialLieu);
                TextView dialogDebut = myDialog.findViewById(R.id.dialDateDebut);
                TextView dialogFin = myDialog.findViewById(R.id.dialDateFin);
                TextView dialogRecurence = myDialog.findViewById(R.id.dialReccurence);
                Button dialModify = myDialog.findViewById(R.id.dialBTModify);
                ImageView dialogImage = myDialog.findViewById(R.id.dialImage);
                dialogDebut.setText(mData.get(vHolder.getAdapterPosition()).getDate_debut());
                dialogDesci_tv.setText(mData.get(vHolder.getAdapterPosition()).getDescription());
                dialogFin.setText(mData.get(vHolder.getAdapterPosition()).getDate_fin());
                switch (mData.get(vHolder.getAdapterPosition()).getImportance()){
                    case 1 : dialogImage.setImageResource(R.drawable.first);
                    case 2 : dialogImage.setImageResource(R.drawable.second);
                    default: dialogImage.setImageResource(R.drawable.third);
                }
                dialogLieu_tv.setText(mData.get(vHolder.getAdapterPosition()).getLieu());
                dialogTitre_tv.setText(mData.get(vHolder.getAdapterPosition()).getTitre());

                dialModify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gson gson = new Gson();
                        Intent intent = ((Activity) mContext).getIntent();
                        intent.putExtra("myEvent", gson.toJson(mData.get(vHolder.getAdapterPosition())));
                        ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Add_Fragment()).commit();
                        myDialog.dismiss();
                    }
                });
                //TO DO : Reccurence to the table
                //dialogRecurence.setText(mData.get(vHolder.getAdapterPosition()).getRecurrence());
                Toast.makeText(mContext, "Test Click" + String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                myDialog.show();
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tvTitre.setText(mData.get(i).getTitre());
        myViewHolder.tvLieu.setText(mData.get(i).getLieu());
        switch (mData.get(i).getImportance()){
            case 1 : myViewHolder.imgImportance.setImageResource(R.drawable.first);
            case 2 : myViewHolder.imgImportance.setImageResource(R.drawable.second);
            default: myViewHolder.imgImportance.setImageResource(R.drawable.third);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitre;
        private TextView tvLieu;
        private ImageView imgImportance;
        private LinearLayout one_event;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            one_event = itemView.findViewById(R.id.one_event_Card);
            tvTitre = itemView.findViewById(R.id.rcTitre);
            tvLieu = itemView.findViewById(R.id.rcLieu);
            imgImportance = itemView.findViewById(R.id.rcImage);

        }
    }
}
