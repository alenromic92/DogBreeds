package com.dogbreeds.network.api;

import com.dogbreeds.network.models.BreedImageResponse;
import com.dogbreeds.network.models.BreedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("breeds/list/all")
    Call<BreedResponse> getBreeds();

    @GET("breed/{breedName}/images/random")
    Call<BreedImageResponse> getBreedImage(@Path("breedName") String breedName);
}
