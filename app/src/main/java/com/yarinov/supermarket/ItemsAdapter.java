package com.yarinov.supermarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private ArrayList<Item> itemList;
    private Context context;

    public ItemsAdapter(Context context,
                        ArrayList<Item> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item currentItem = itemList.get(position);

        holder.itemTitleLabel.setText(currentItem.getName());
        holder.itemPriceLabel.setText(currentItem.getPrice() + " ₪");

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemPriceLabel, itemTitleLabel;
        ImageView itemImage;
        Button addToCartButton;

        public MyViewHolder(View v) {
            super(v);

            itemPriceLabel = v.findViewById(R.id.itemPriceLabel);
            itemTitleLabel = v.findViewById(R.id.itemTitleLabel);
            itemImage = v.findViewById(R.id.itemImage);
            addToCartButton = v.findViewById(R.id.addToCartButton);

        }
    }


}
