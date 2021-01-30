package com.android.kevng2.freestuff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {
    private List<Item> mData;

    public Adapter(List<Item> mData) {
        this.mData = mData;
    }

    private Context mContext;

    @NonNull
    @Override
    public Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Glide
                .with(mContext)
                .load(mData.get(position).getImage())
                .override(200)
                .fitCenter()
                .into(holder.ivItem);

        holder.tvTitle.setText(mData.get(position).getName());
        holder.tvStatus.setText(mData.get(position).getStatus());
        holder.tvCondition.setText(mData.get(position).getCondition());
        holder.tvDescription.setText(mData.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView ivItem;
        TextView tvTitle, tvStatus, tvCondition, tvDescription;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ivItem = itemView.findViewById(R.id.ivItem);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvCondition = itemView.findViewById(R.id.tvCondition);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
