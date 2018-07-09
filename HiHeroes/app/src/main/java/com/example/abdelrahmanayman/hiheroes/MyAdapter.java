package com.example.abdelrahmanayman.hiheroes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<ListItem> listItems ;
    Context context ;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item , parent , false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ListItem listItem = listItems.get(position);
        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDesc.setText(listItem.getDesc());

        Picasso.get()
                .load(listItem.getImageURL())
                .into(holder.imageViewImage);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewHead;
        TextView textViewDesc;
        ImageView imageViewImage ;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewDesc = itemView.findViewById(R.id.tvDescription);
            textViewHead = itemView.findViewById(R.id.tvheading);
            imageViewImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
