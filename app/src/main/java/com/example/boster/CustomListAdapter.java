package com.example.boster;

import android.content.Context;
import android.util.Log;
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
        holder.dateView.setText(deplacement.getMode());
        holder.villeView.setText(deplacement.getVille());
        holder.distanceView.setText(deplacement.getDistance() + " km parcourus");
        holder.co2View.setText(deplacement.getCo2() + " kg évités");

        //int imageId = this.getMipmapResIdByName(deplacement.getFlagName());

        if(deplacement.getMode()=="Apied"){
            holder.modeTransportView.setImageResource(R.drawable.voiture);
        }else{
            holder.modeTransportView.setImageResource(R.drawable.moto);
        }

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView modeTransportView;
        TextView dateView;
        TextView villeView;
        TextView distanceView;
        TextView co2View;
    }

}