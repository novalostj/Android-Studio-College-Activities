package com.example.activity5;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CardViewHolder> {
    private final String[] titleNameArray;
    private final String[] descriptionArray;
    private final TypedArray imageIDArray;
    private final Context context;


    public RecyclerAdapter(String[] titleNameList, String[] descriptionList, TypedArray imageIDList, Context context) {
        this.titleNameArray = titleNameList;
        this.descriptionArray = descriptionList;
        this.imageIDArray = imageIDList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.titleNameTextView.setText(titleNameArray[position]);
        holder.descriptionTextView.setText(descriptionArray[position]);
        holder.imageView.setImageResource(imageIDArray.getResourceId(position, 0));
        holder.cardView.setOnClickListener(view -> ToastMessage(titleNameArray[position]));
    }

    @Override
    public int getItemCount() {
        return titleNameArray.length;
    }

    private void ToastMessage(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleNameTextView;
        private final TextView descriptionTextView;
        private final ImageView imageView;
        private final CardView cardView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            titleNameTextView = itemView.findViewById(R.id.titleText);
            descriptionTextView = itemView.findViewById(R.id.descriptionText);
            imageView = itemView.findViewById(R.id.profile_image);
            cardView = itemView.findViewById(R.id.designCardView);
        }
    }
}


