package com.example.cooking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleTranslateApi {
    @GET("/language/translate/v2")
    Call<TranslateResponse> translate(
            @Query("q") String query,
            @Query("target") String targetLanguage,
            @Query("key") String apiKey
    );
}
