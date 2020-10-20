package com.example.scan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RvAdapterOneInput extends RecyclerView.Adapter<RvAdapterOneInput.RvViewHolder> {
    Context context;
    ArrayList<Model> models;
    Onclick onclick;
    public interface Onclick {
        void onEvent(Model model, int pos);
    }
    public RvAdapterOneInput(Context context, ArrayList<Model> models, Onclick onclick) {
        this.context = context;
        this.models = models;
        this.onclick = onclick;
    }
    View view;
    @Override
    public RvAdapterOneInput.RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.from(parent.getContext()).inflate(R.layout.item_list_basics, parent, false);
        RvViewHolder rvViewHolder = new RvViewHolder(view);
        return rvViewHolder;
    }
    @Override
    public void onBindViewHolder(RvAdapterOneInput.RvViewHolder holder, final int position) {
        final Model model = models.get(position);
        if (model.getValue() != null) {
            holder.itemName.setText(model.getValue());
        }
        holder.removeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                models.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.onEvent(model,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return models.size();
    }
    public class RvViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        ImageView removeImg;
        LinearLayout llItem;
        public RvViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.tv_name);
            removeImg = itemView.findViewById(R.id.img_remove);
            llItem = itemView.findViewById(R.id.ll_item);
        }
    }
}
