package com.dhian.daftarbelanja;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.Collections;
import java.util.List;
import android.graphics.drawable.*;
import android.widget.CompoundButton;
import android.content.*;
import android.preference.*;

public class RecycleViewAdapter extends RecyclerView.Adapter<ViewHolder> implements CompoundButton.OnCheckedChangeListener{

    List<Data> list = Collections.emptyList();
    Context context;
    //Boolean[] checkedStatus;
    
    //private SharedPreferences prefs;

    public RecycleViewAdapter(List<Data> list ,Context context) {
        this.list = list;
        this.context = context;
       // this.checkedStatus = checkedStatus;
        //prefs = PreferenceManager.getDefaultSharedPreferences(context);
        
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nomor.setText(String.valueOf(position+1)+".");
        holder.namaBarang.setText(list.get(position).sNamaBarang);
        holder.jumlah.setText(list.get(position).sJumlah+" pcs");
        holder.check.setTag(position);
        holder.check.setOnCheckedChangeListener(this);
        //SharedPreferences sharedPreferences= context.getSharedPreferences("status", Context.MODE_PRIVATE);
        //String sCheck = sharedPreferences.getString(list.get(position).sNamaBarang,"false");
        holder.check.setChecked(convertToBoolean(list.get(position).sCheck));
        

        //animate(holder);
    }
    
    
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int index = (int) buttonView.getTag();
        //checkedStatus[index] = isChecked;
        String key = String.valueOf(index);

        SharedPreferences sharedPreferences= context.getSharedPreferences("status", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(list.get(index).sNamaBarang+"cb",String.valueOf(isChecked));
        editor.apply();
    }
    
    public boolean convertToBoolean(String value) {
        boolean returnValue = false;
        if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || 
            "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value))
            returnValue = true;
        return returnValue;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView
    public void insert(int position, Data data) {
        list.add(position, data);
        notifyItemInserted(position);
    }
    // Remove a RecyclerView item containing the Data object
    public void remove(Data data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }
    public void removeAll(){
        list.removeAll(list);
        notifyDataSetChanged();
    }
    
    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    // public void animate(RecyclerView.ViewHolder viewHolder) {
    // final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate_overshoot_interpolator);
    //  viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    //  }

}
