package com.cagataykolus.orderapp.ui.main.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cagataykolus.orderapp.R;
import com.cagataykolus.orderapp.model.ProductDetail;


public class InnerRecyclerViewAdapter extends RecyclerView.Adapter<InnerRecyclerViewAdapter.ViewHolder> {
    private ProductDetail productDetail;

    InnerRecyclerViewAdapter(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderDetail;
        TextView tvSummaryPrice;

        ViewHolder(View itemView) {
            super(itemView);
            tvOrderDetail = itemView.findViewById(R.id.tv_orderDetail);
            tvSummaryPrice = itemView.findViewById(R.id.tv_summaryPrice);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_product_detail, parent, false);
        InnerRecyclerViewAdapter.ViewHolder vh = new InnerRecyclerViewAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Resources res = holder.itemView.getContext().getResources();
        String summaryPriceWithCurrency = productDetail.getSummaryPrice().toString() + " " + res.getString(R.string.currency);

        holder.tvOrderDetail.setText(productDetail.getOrderDetail());
        holder.tvSummaryPrice.setText(summaryPriceWithCurrency);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}