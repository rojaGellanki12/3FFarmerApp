package com.calibrage.a3ffarmerapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calibrage.a3ffarmerapp.Model.FertilizerModel;
import com.calibrage.a3ffarmerapp.Model.GetRecommendationsModel;
import com.calibrage.a3ffarmerapp.Model.MovieModal;
import com.calibrage.a3ffarmerapp.Model.PaymentHistoryModel;
import com.calibrage.a3ffarmerapp.R;

import java.util.List;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.ViewHolder> {


    private Context context;

    //List of superHeroes
    List<PaymentHistoryModel> superHeroes;

    public PaymentHistoryAdapter(List<PaymentHistoryModel> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_history_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PaymentHistoryModel superHero =  superHeroes.get(position);

        holder.txtDateOfPayment.setText(superHero.getDateOfPayment());
        holder.txtAmount.setText(superHero.getAmount());
        holder.txtBankAccountNumber.setText(superHero.getBankAccountNumber());
        holder.txtBankHolderName.setText(superHero.getBankHolderName());
        holder.txtBankName.setText(superHero.getBankName());
        holder.finalAmount.setText(superHero.getFinalAmount());
        /*holder.year3.setText(superHero.getYear3());
        holder.year4And5.setText(superHero.getYear4And5());
        holder.year6And7.setText(superHero.getYear6And7());*/
        //  holder.year8To17.setText(superHero.getYear8To17());
        //   holder.remarks.setText(superHero.getRemarks());
        String powers = "";

        for(int i = 0; i<superHero.getPowers().size(); i++){
            powers+= superHero.getPowers().get(i);
        }

        holder.textViewPowers.setText(powers);
    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtLedgerItem;
        public TextView txtDateOfPayment;
        public TextView txtAmount;
        public TextView txtBankAccountNumber;
        public TextView txtBankHolderName;
        public TextView txtBankName;
        public TextView showMore;
        public TextView finalAmount;
        public LinearLayout contentLayout;
        public TextView  remarks,textViewPowers;

        public ViewHolder(View itemView) {
            super(itemView);

            //   txtLedgerItem = itemView.findViewById(R.id.ledgerItem);
            txtDateOfPayment = itemView.findViewById(R.id.dateOfPayment);
            txtAmount = itemView.findViewById(R.id.amount);
            txtBankAccountNumber = itemView.findViewById(R.id.bankAccountNumber);
            txtBankHolderName = itemView.findViewById(R.id.bankHolderName);
            txtBankName = itemView.findViewById(R.id.bankName);
            finalAmount = itemView.findViewById(R.id.amount1);
            //   showMore = (TextView)itemView.findViewById(R.id.show_more);
            contentLayout = (LinearLayout)itemView.findViewById(R.id.content);
            //   year8To17= (TextView) itemView.findViewById(R.id.year8To17);
            //  remarks= (TextView) itemView.findViewById(R.id.remarks);


            textViewPowers= (TextView) itemView.findViewById(R.id.textViewPowers);
        }
    }
}
/*

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.ViewHolder>{
    private PaymentHistoryModel[] listdata;
    public Context mContext;
    private Animation animationUp, animationDown;
    private final int COUNTDOWN_RUNNING_TIME = 500;

    // RecyclerView recyclerView;
    public PaymentHistoryAdapter(Context ctx, PaymentHistoryModel[] listdata, Animation animationUp, Animation animationDown) {
        this.listdata = listdata;
        this.animationDown = animationDown;
        this.animationUp = animationUp;
        this.mContext=ctx;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.payment_history_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PaymentHistoryModel myListData = listdata[position];
   //     holder.txtLedgerItem.setText(listdata[position].getLedgerItem());
        holder.txtDateOfPayment.setText(listdata[position].getDateOfPayment());
        holder.txtAmount.setText(listdata[position].getAmount());
        holder.txtBankAccountNumber.setText(listdata[position].getBankAccountNumber());
        holder.txtBankHolderName.setText(listdata[position].getBankHolderName());
        holder.txtBankName.setText(listdata[position].getBankName());


        //      holder.imageView.setImageResource(listdata[position].getImgId());
      */
/*  holder.showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.contentLayout.isShown()) {
                    holder.contentLayout.setAnimation(animationUp);

                    CountDownTimer countDownTimerStatic = new CountDownTimer(COUNTDOWN_RUNNING_TIME, 16) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }

                        @Override
                        public void onFinish() {
                            holder.contentLayout.setVisibility(View.GONE);
                        }
                    };
                    countDownTimerStatic.start();

                    //  rowViewHolder.showMore.setText(context.getString(R.string.show));
                    holder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_dow, 0);
                } else {
                    holder.contentLayout.setVisibility(View.VISIBLE);
                    holder.contentLayout.setAnimation(animationDown);

                    //   rowViewHolder.showMore.setText(context.getString(R.string.hide));
                    holder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
                }
            }
        });*//*

    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtLedgerItem;
        public TextView txtDateOfPayment;
        public TextView txtAmount;
        public TextView txtBankAccountNumber;
        public TextView txtBankHolderName;
        public TextView txtBankName;
        public TextView showMore;
        public LinearLayout contentLayout;


        public ViewHolder(View itemView) {
            super(itemView);

         //   txtLedgerItem = itemView.findViewById(R.id.ledgerItem);
            txtDateOfPayment = itemView.findViewById(R.id.dateOfPayment);
            txtAmount = itemView.findViewById(R.id.amount);
            txtBankAccountNumber = itemView.findViewById(R.id.bankAccountNumber);
            txtBankHolderName = itemView.findViewById(R.id.bankHolderName);
            txtBankName = itemView.findViewById(R.id.bankName);
         //   showMore = (TextView)itemView.findViewById(R.id.show_more);
            contentLayout = (LinearLayout)itemView.findViewById(R.id.content);
        }


    }
}*/
/*
public class PaymentHistoryAdapter extends RecyclerView.Adapter {
    List<PaymentHistoryModel> movieList;
    private LayoutInflater layoutInflater;
    private Animation animationUp, animationDown;
    private Context context;
    private final int COUNTDOWN_RUNNING_TIME = 500;
    public PaymentHistoryAdapter(Context context,List<PaymentHistoryModel> movieList, Animation animationUp, Animation animationDown) {
        this.movieList = movieList;

        this.animationDown = animationDown;
        this.animationUp = animationUp;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.payment_history_list_item, parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      final  RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            rowViewHolder.txtLedgerItem.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtDateOfPayment.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtAmount.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtBankAccountNumber.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtBankHolderName.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtBankName.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.showMore.setClickable(false);//setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtLedgerItem.setText("Ledger Item");
            rowViewHolder.txtLedgerItem.setTextColor(Color.WHITE);
            rowViewHolder.txtDateOfPayment.setText("Date Of Payment");
            rowViewHolder.txtDateOfPayment.setTextColor(Color.WHITE);
            rowViewHolder.txtAmount.setText("Amount");
            rowViewHolder.txtAmount.setTextColor(Color.WHITE);
            rowViewHolder.txtBankAccountNumber.setText("Bank Account Number");
            rowViewHolder.txtBankAccountNumber.setTextColor(Color.WHITE);
            rowViewHolder.txtBankHolderName.setText("Bank Holder Name");
            rowViewHolder.txtBankHolderName.setTextColor(Color.WHITE);
            rowViewHolder.txtBankName.setText("Bank Name");
            rowViewHolder.txtBankName.setTextColor(Color.WHITE);
            rowViewHolder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            rowViewHolder.showMore.setClickable(false);

        } else {
            PaymentHistoryModel modal = movieList.get(rowPos-1);

            // Content Cells. Content appear here
            rowViewHolder.txtLedgerItem.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtDateOfPayment.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtAmount.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtBankAccountNumber.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtBankHolderName.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtBankName.setBackgroundResource(R.drawable.table_content_cell_bg);


            rowViewHolder.txtLedgerItem.setText(modal.getLedgerItem()+"");
            rowViewHolder.txtDateOfPayment.setText(modal.getDateOfPayment());
            rowViewHolder.txtAmount.setText(modal.getAmount()+"");
            rowViewHolder.txtBankAccountNumber.setText(modal.getBankAccountNumber()+"");
            rowViewHolder.txtBankHolderName.setText(modal.getBankHolderName()+"");
            rowViewHolder.txtBankName.setText(modal.getBankName()+"");
        }
        rowViewHolder.showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rowViewHolder.contentLayout.isShown()) {
                    rowViewHolder.contentLayout.startAnimation(animationUp);

                    CountDownTimer countDownTimerStatic = new CountDownTimer(COUNTDOWN_RUNNING_TIME, 16) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }

                        @Override
                        public void onFinish() {
                            rowViewHolder.contentLayout.setVisibility(View.GONE);
                        }
                    };
                    countDownTimerStatic.start();

                  //  rowViewHolder.showMore.setText(context.getString(R.string.show));
                    rowViewHolder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_dow, 0);
                } else {
                    rowViewHolder.contentLayout.setVisibility(View.VISIBLE);
                    rowViewHolder.contentLayout.startAnimation(animationDown);

                 //   rowViewHolder.showMore.setText(context.getString(R.string.hide));
                    rowViewHolder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size()+1; // one more to add header row
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtLedgerItem;
        protected TextView txtDateOfPayment;
        protected TextView txtAmount;
        protected TextView txtBankAccountNumber;
        protected TextView txtBankHolderName;
        protected TextView txtBankName,showMore;
        protected LinearLayout contentLayout;

        public RowViewHolder(View itemView) {
            super(itemView);

            txtLedgerItem = itemView.findViewById(R.id.ledgerItem);
            txtDateOfPayment = itemView.findViewById(R.id.dateOfPayment);
            txtAmount = itemView.findViewById(R.id.amount);
            txtBankAccountNumber = itemView.findViewById(R.id.bankAccountNumber);
            txtBankHolderName = itemView.findViewById(R.id.bankHolderName);
            txtBankName = itemView.findViewById(R.id.bankName);
            showMore = (TextView)itemView.findViewById(R.id.show_more);
            contentLayout = (LinearLayout)itemView.findViewById(R.id.content);

        }
    }
}
*/
