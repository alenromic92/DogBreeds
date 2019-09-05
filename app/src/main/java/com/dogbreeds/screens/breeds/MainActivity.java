package com.dogbreeds.screens.breeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dogbreeds.R;
import com.dogbreeds.network.api.ApiServiceFactory;
import com.dogbreeds.network.models.BreedResponse;
import com.dogbreeds.screens.breed.BreedActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private BreedsAdapter adapter;
    private ArrayList<String> breeds = new ArrayList<>();
    private Call<BreedResponse> getBreedsCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycler();
        getBreeds();
    }

    private void initRecycler() {
        RecyclerView breedsRecyclerView = findViewById(R.id.breedsRecyclerView);
        adapter = new BreedsAdapter(breeds, breed -> {
            Intent intent = new Intent(MainActivity.this, BreedActivity.class);
            intent.putExtra("breed", breed);
            startActivity(intent);
        });
        breedsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        breedsRecyclerView.setAdapter(adapter);
    }

    private void getBreeds() {
        getBreedsCall = ApiServiceFactory.getApiService().getBreeds();
        getBreedsCall.enqueue(new Callback<BreedResponse>() {
            @Override
            public void onResponse(Call<BreedResponse> call, Response<BreedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (String breed : response.body().getMessage().keySet()) {
                        breeds.add(breed.substring(0, 1).toUpperCase() + breed.substring(1));
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<BreedResponse> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getBreedsCall != null) {
            getBreedsCall.cancel();
        }
    }
}
