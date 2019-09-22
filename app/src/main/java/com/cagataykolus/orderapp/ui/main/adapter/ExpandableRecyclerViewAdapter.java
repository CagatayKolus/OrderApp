package com.cagataykolus.orderapp.ui.main.adapter;

import android.content.Context;
import android.content.res.Resources;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cagataykolus.orderapp.R;
import com.cagataykolus.orderapp.model.Order;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class ExpandableRecyclerViewAdapter extends RecyclerView.Adapter<ExpandableRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Integer> counter = new ArrayList<>();
    private List<Order> orderList;

    public ExpandableRecyclerViewAdapter(Context context, List<Order> orderList) {
        this.orderList = orderList;
        this.context = context;

        for (int i = 0; i < orderList.size(); i++) {
            counter.add(0);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvProductRecyclerView;
        CardView cvProductCardView;

        TextView tvMarketName;
        TextView tvOrderName;
        TextView tvProductState;
        TextView tvProductPrice;
        TextView tvDate;
        TextView tvMonth;

        ViewHolder(View itemView) {
            super(itemView);

            rvProductRecyclerView = itemView.findViewById(R.id.rv_productRecyclerView);
            cvProductCardView = itemView.findViewById(R.id.cv_productCardView);

            tvMarketName = itemView.findViewById(R.id.tv_marketName);
            tvOrderName = itemView.findViewById(R.id.tv_orderName);
            tvProductState = itemView.findViewById(R.id.tv_productState);
            tvProductPrice = itemView.findViewById(R.id.tv_productPrice);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvMonth = itemView.findViewById(R.id.tv_month);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order, parent, false);
        ExpandableRecyclerViewAdapter.ViewHolder vh = new ExpandableRecyclerViewAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Resources res = holder.itemView.getContext().getResources();
        String productPriceWithCurrency = orderList.get(position).getProductPrice().toString() + " " + res.getString(R.string.currency);

        holder.tvMarketName.setText(orderList.get(position).getMarketName());
        holder.tvOrderName.setText(orderList.get(position).getOrderName());
        holder.tvProductState.setText(orderList.get(position).getProductState());
        holder.tvProductPrice.setText(productPriceWithCurrency);
        holder.tvDate.setText(orderList.get(position).getDate());
        holder.tvMonth.setText(getMonth(Integer.parseInt(orderList.get(position).getMonth())));

        // Set background color for product state
        setColorProductState(holder.tvProductState);

        InnerRecyclerViewAdapter itemInnerRecyclerView = new InnerRecyclerViewAdapter(orderList.get(position).getProductDetail());
        holder.rvProductRecyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        holder.cvProductCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter.get(position) % 2 == 0) {
                    holder.rvProductRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    holder.rvProductRecyclerView.setVisibility(View.GONE);
                }

                counter.set(position, counter.get(position) + 1);

            }
        });
        holder.rvProductRecyclerView.setAdapter(itemInnerRecyclerView);

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    // This method returns string value of month
    private String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }

    // Method for set background color for product state
    private void setColorProductState(TextView textView) {
        if (textView.getText().equals(context.getString(R.string.productStateGreen))) {
            textView.setBackgroundResource(R.color.productStateColorGreen);
        } else if (textView.getText().equals(context.getString(R.string.productStateOrange))) {
            textView.setBackgroundResource(R.color.productStateColorOrange);
        } else if (textView.getText().equals(context.getString(R.string.productStateRed))) {
            textView.setBackgroundResource(R.color.productStateColorRed);
        }
    }
}