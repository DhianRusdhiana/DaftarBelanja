package com.dhian.daftarbelanja;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.*;


//The adapters View Holder
public class ViewHolder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView nomor;
    TextView namaBarang;
    TextView jumlah;
    CheckBox check;

    ViewHolder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        nomor = (TextView)itemView.findViewById(R.id.nomor);
        namaBarang = (TextView) itemView.findViewById(R.id.namaBarang);
        jumlah = (TextView) itemView.findViewById(R.id.jumlah);
        check = (CheckBox) itemView.findViewById(R.id.check);
    }

}
