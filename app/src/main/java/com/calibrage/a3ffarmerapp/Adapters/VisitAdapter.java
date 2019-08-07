package com.calibrage.a3ffarmerapp.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.calibrage.a3ffarmerapp.Model.Model;
import com.calibrage.a3ffarmerapp.Model.visitModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.ArrayList;

public class VisitAdapter extends BaseAdapter {

    public ArrayList<visitModel> productList;
    Activity activity;

    public VisitAdapter(Activity activity, ArrayList<visitModel> productList) {
        super();
        this.activity = activity;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private String plotId;
    private String farmerId;
    private String farmerName;
    private String plotArea;
    private String plotStatus;
    private String plotVillage;
    private String plotMandal;
    private String plotDistrict;
    private String comments;
    private String dateOfRequests;
    private class ViewHolder {
        TextView plotId;
        TextView farmerId;
        TextView farmerName;
        TextView plotArea;
        TextView plotStatus;
        TextView plotVillage;
        TextView plotMandal;
        TextView plotDistrict;
        TextView comments;
        TextView dateOfRequests;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.visitview_row, null);
            holder = new ViewHolder();
            holder.plotId = (TextView) convertView.findViewById(R.id.plotId);
            holder.farmerId = (TextView) convertView.findViewById(R.id.farmerId);
            holder.farmerName = (TextView) convertView
                    .findViewById(R.id.farmerName);
            holder.comments = (TextView) convertView.findViewById(R.id.comments);
            convertView.setTag(holder);
            holder.plotArea = (TextView) convertView.findViewById(R.id.plotArea);
            holder.plotStatus = (TextView) convertView.findViewById(R.id.plotStatus);
            holder.plotVillage = (TextView) convertView
                    .findViewById(R.id.plotVillage);
            holder.plotMandal = (TextView) convertView.findViewById(R.id.plotMandal);
            holder.plotDistrict = (TextView) convertView.findViewById(R.id.plotDistrict);
            holder.dateOfRequests = (TextView) convertView
                    .findViewById(R.id.dateOfRequests);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        visitModel item = productList.get(position);
        holder.plotId.setText(item.getPlotId().toString());
        holder.farmerId.setText(item.getFarmerId().toString());
        holder.farmerName.setText(item.getFarmerName().toString());
        holder.comments.setText(item.getComments().toString());
        holder.plotArea.setText(item.getPlotArea().toString());
        holder.plotStatus.setText(item.getPlotStatus().toString());
        holder.plotVillage.setText(item.getPlotVillage().toString());
        holder.plotMandal.setText(item.getPlotMandal().toString());
        holder.plotDistrict.setText(item.getPlotDistrict().toString());
        holder.dateOfRequests.setText(item.getDateOfRequests().toString());

        return convertView;
    }
}
