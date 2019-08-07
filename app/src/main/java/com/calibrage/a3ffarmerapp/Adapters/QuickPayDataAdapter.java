package com.calibrage.a3ffarmerapp.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Activities.QuickPayActivity;
import com.calibrage.a3ffarmerapp.R;
import com.calibrage.a3ffarmerapp.Model.QuickPayModel;

import java.util.List;

public class QuickPayDataAdapter extends
		RecyclerView.Adapter<QuickPayDataAdapter.ViewHolder> {
	public Context mContext;
	private List<QuickPayModel> stList;
	private OnClickAck onClickAck ;

	public QuickPayDataAdapter(Context ctx,List<QuickPayModel> students) {
		this.stList = students;
		this.mContext=ctx;
	}


	// Create new views
	@Override
	public QuickPayDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
		// create a new view
		View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.quickpaycardview_row, null);

		// create ViewHolder

		ViewHolder viewHolder = new ViewHolder(itemLayoutView);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, final int position) {

		final int pos = position;

		viewHolder.tvName.setText(stList.get(position).getName());

		viewHolder.tvNetWeight.setText(stList.get(position).getNetWeight());

		viewHolder.tvDate.setText(stList.get(position).getDate());

		viewHolder.tvCc.setText(stList.get(position).getCc());

		viewHolder.chkSelected.setChecked(stList.get(position).isSelected());

		viewHolder.chkSelected.setTag(stList.get(position));

		
		viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CheckBox cb = (CheckBox) v;
				QuickPayModel contact = (QuickPayModel) cb.getTag();

				contact.setSelected(cb.isChecked());
				stList.get(pos).setSelected(cb.isChecked());
				onClickAck.setOnClickAckListener("",pos,cb.isChecked());


			}
		});

	}

	// Return the size arraylist
	@Override
	public int getItemCount() {
		return stList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView tvName;
		public TextView tvNetWeight;
		public TextView tvCc;
		public TextView tvDate;
		public CheckBox chkSelected;

		public QuickPayModel singlestudent;

		public ViewHolder(View itemLayoutView) {
			super(itemLayoutView);

			tvName = (TextView) itemLayoutView.findViewById(R.id.tvName);
			tvNetWeight = (TextView) itemLayoutView.findViewById(R.id.tvNetWeight);
			tvDate = (TextView) itemLayoutView.findViewById(R.id.tvDate);
			tvCc = (TextView) itemLayoutView.findViewById(R.id.tvCc);

			chkSelected = (CheckBox) itemLayoutView
					.findViewById(R.id.chkSelected);

		}

	}

	// method to access in activity after updating selection
	public List<QuickPayModel> getStudentist() {
		return stList;
	}


	public void setOnListener(OnClickAck OListener) {
		this.onClickAck = OListener;
	}

	public interface OnClickAck {
		void setOnClickAckListener(String status, int position,Boolean ischecked);
	}

}
