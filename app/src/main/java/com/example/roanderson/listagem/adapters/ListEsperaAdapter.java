package com.example.roanderson.listagem.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.roanderson.listagem.R;
import com.example.roanderson.listagem.models.ListEspera;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Roanderson on 24/08/2016.
 */
public class ListEsperaAdapter extends RecyclerView.Adapter<ListEsperaAdapter.MyViewHolder>  {
    ArrayList<ListEspera> Items;
    Context context;
    public ListEsperaAdapter(Context context,ArrayList<ListEspera>feedItems){
        this.Items=feedItems;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_consulta,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        YoYo.with(Techniques.FadeIn).playOn(holder.recyclerView);
        final ListEspera current=Items.get(position);
        holder.Title.setText(current.getNome());
        Date data = null;


        holder.hora.setText(current.getHora());


        if(current.getStatus()==0){
            holder.Thumbnail.setBackgroundResource(R.drawable.traco3);
            holder.Description.setText("Em espera");

        }else if(current.getStatus()==2){
            holder.Thumbnail.setBackgroundResource(R.drawable.traco2);
            holder.Description.setText("Atendido");

        }else if(current.getStatus()==1){
            holder.Thumbnail.setBackgroundResource(R.drawable.traco1);
            holder.Description.setText("Em Atendimento");

        }





    }



    @Override
    public int getItemCount() {
        return Items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title,Description,hora;
        ImageView Thumbnail;
        RecyclerView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.tv_nome);
            Description= (TextView) itemView.findViewById(R.id.tv_categoria);
            hora= (TextView) itemView.findViewById(R.id.hora);
            cardView= (RecyclerView) itemView.findViewById(R.id.recyclerview);
            Thumbnail= (ImageView) itemView.findViewById(R.id.iv_imgProduto);
        }
    }
}
