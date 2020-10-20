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

public class RvAdapterFourInputs extends RecyclerView.Adapter<RvAdapterFourInputs.RvViewHolder> {
    Context context;
    ArrayList<Model> models;
   Onclick onclick;


    public interface Onclick {
        void onEvent(Model model, int pos);
    }
    public RvAdapterFourInputs(Context context, ArrayList<Model> models, Onclick onclick) {
        this.context = context;
        this.models = models;
        this.onclick = onclick;
    }
    View view;
    @Override
    public RvAdapterFourInputs.RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.from(parent.getContext()).inflate(R.layout.item_dist_kendall, parent, false);
        RvAdapterFourInputs.RvViewHolder rvViewHolder = new RvAdapterFourInputs.RvViewHolder(view);
        return rvViewHolder;
    }
    @Override
    public void onBindViewHolder(RvAdapterFourInputs.RvViewHolder holder, final int position) {
        final Model model = models.get(position);
        if (model.getValue() != null && model.getValue2() != null && model.getValue3() != null && model.getValue4() != null) {
            holder.itemName1.setText(model.getValue());
            holder.itemName2.setText(model.getValue2());
            holder.itemName3.setText(model.getValue3());
            holder.itemName4.setText(model.getValue4());

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
        TextView itemName1, itemName2, itemName3, itemName4;
        ImageView removeImg;
        LinearLayout llItem;
        public RvViewHolder(View itemView) {
            super(itemView);
            itemName1 = itemView.findViewById(R.id.tv_name1);
            itemName2 = itemView.findViewById(R.id.tv_name2);
            itemName3 = itemView.findViewById(R.id.tv_name3);
            itemName4 = itemView.findViewById(R.id.tv_name4);


            removeImg = itemView.findViewById(R.id.img_remove);

            llItem = itemView.findViewById(R.id.ll_item);
        }
    }
}
