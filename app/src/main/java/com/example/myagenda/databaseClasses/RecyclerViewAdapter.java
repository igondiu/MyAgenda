package com.example.myagenda.databaseClasses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myagenda.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Agenda_Class> mData;

    public RecyclerViewAdapter(Context mContext, List<Agenda_Class> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.one_event, viewGroup, false);
        MyViewHolder vHolder = new MyViewHolder(v);
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


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitre = itemView.findViewById(R.id.rcTitre);
            tvLieu = itemView.findViewById(R.id.rcLieu);
            imgImportance = itemView.findViewById(R.id.rcImage);

        }
    }
}
