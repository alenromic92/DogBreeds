package com.dogbreeds.screens.breeds;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dogbreeds.R;

import java.util.ArrayList;

public class BreedsAdapter extends RecyclerView.Adapter<BreedsAdapter.BreedHolder> {

    private ArrayList<String> breeds;
    private OnItemClickListener listener;

    public BreedsAdapter(ArrayList<String> breeds, OnItemClickListener listener) {
        this.breeds = breeds;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BreedHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BreedHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_breed, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BreedHolder breedHolder, int i) {
        final String breed = breeds.get(i);
        TextView breedName = breedHolder.itemView.findViewById(R.id.breedName);
        breedName.setText(breed);
        breedHolder.itemView.setOnClickListener(view -> listener.onItemClick(breed));
    }

    @Override
    public int getItemCount() {
        return breeds.size();
    }

    class BreedHolder extends RecyclerView.ViewHolder {
        public BreedHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
