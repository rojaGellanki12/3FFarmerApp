package com.calibrage.a3ffarmerapp.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.calibrage.a3ffarmerapp.Model.Model;
import com.calibrage.a3ffarmerapp.R;

import java.util.ArrayList;

public class listviewAdapter extends BaseAdapter {

    public ArrayList<Model> productList;
    Activity activity;

    public listviewAdapter(Activity activity, ArrayList<Model> productList) {
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

    private class ViewHolder {
        TextView ploteCode;
        TextView fertilizer;
        TextView dosage;
        TextView comments;
        TextView issue;
        TextView recommededBy;
        TextView recommededOn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.ploteCode = (TextView) convertView.findViewById(R.id.ploteCode);
            holder.fertilizer = (TextView) convertView.findViewById(R.id.fertilizer);
            holder.dosage = (TextView) convertView
                    .findViewById(R.id.dosage);
            holder.comments = (TextView) convertView.findViewById(R.id.comments);
            convertView.setTag(holder);
            holder.issue = (TextView) convertView.findViewById(R.id.issue);
            holder.recommededBy = (TextView) convertView.findViewById(R.id.recommendedBy);
            holder.recommededOn = (TextView) convertView
                    .findViewById(R.id.recommendedOn);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Model item = productList.get(position);
        holder.ploteCode.setText(item.getPloteCode().toString());
        holder.fertilizer.setText(item.getFertilizer().toString());
        holder.dosage.setText(item.getDosage().toString());
        holder.comments.setText(item.getComments().toString());
        holder.issue.setText(item.getIssue().toString());
        holder.recommededBy.setText(item.getRecommendedBy().toString());
        holder.recommededOn.setText(item.getRecommededOn().toString());

        return convertView;
    }
}
