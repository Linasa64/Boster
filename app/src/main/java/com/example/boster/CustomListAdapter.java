package com.example.boster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter  extends BaseAdapter {

    private final List<Deplacement> listData;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public CustomListAdapter(Context aContext,  List<Deplacement> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_liste_activites, null);
            holder = new ViewHolder();
            holder.modeTransportView = (ImageView) convertView.findViewById(R.id.iconeTransport);
            holder.dateView = (TextView) convertView.findViewById(R.id.dateActivity);
            holder.villeView = (TextView) convertView.findViewById(R.id.villeActivity);
            holder.distanceView = (TextView) convertView.findViewById(R.id.distanceActivity);
            holder.co2View = (TextView) convertView.findViewById(R.id.co2Activity);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Deplacement deplacement = this.listData.get(position);
        holder.dateView.setText(deplacement.getDate());
        holder.villeView.setText(deplacement.getVille());
        holder.distanceView.setText(deplacement.getDistance() + " km");
        holder.co2View.setText(deplacement.getCo2() + " kg ??vit??s");

        String mode = deplacement.getMode();
        if(mode.equals("Marche")){
            holder.modeTransportView.setImageResource(R.drawable.marche);
        }else {
            holder.modeTransportView.setImageResource(R.drawable.velo);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView modeTransportView;
        TextView dateView;
        TextView villeView;
        TextView distanceView;
        TextView co2View;
    }

}