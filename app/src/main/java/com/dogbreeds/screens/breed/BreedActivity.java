package com.dogbreeds.screens.breed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dogbreeds.R;
import com.dogbreeds.network.api.ApiServiceFactory;
import com.dogbreeds.network.models.BreedImageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedActivity extends AppCompatActivity {

    private ImageView breedImage;
    private String breed;
    private Call<BreedImageResponse> getBreedImageCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed);
        breed = getIntent().getStringExtra("breed");
        initViews();
        getBreedImage();
    }

    private void initViews() {
        TextView breedTitle = findViewById(R.id.breedTitle);
        breedImage = findViewById(R.id.breedImage);
        breedTitle.setText(breed);
    }

    private void getBreedImage() {
        getBreedImageCall = ApiServiceFactory.getApiService().getBreedImage(breed.toLowerCase());
        getBreedImageCall.enqueue(new Callback<BreedImageResponse>() {
            @Override
            public void onResponse(Call<BreedImageResponse> call, Response<BreedImageResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Glide.with(breedImage)
                            .load(response.body().getMessage())
                            .into(breedImage);
                }
            }

            @Override
            public void onFailure(Call<BreedImageResponse> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getBreedImageCall != null) {
            getBreedImageCall.cancel();
        }
    }
}
