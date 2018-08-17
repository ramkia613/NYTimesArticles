package com.nytimes.articles.data.repository;


import com.nytimes.articles.data.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApplicationAPI {
    String BASE_URL = "http://api.nytimes.com";

    @GET("svc/mostpopular/v2/mostviewed/{sections}/{period}.json")
    Call<Result> articles(@Path("sections") String sections,
                          @Path("period") String period,
                          @Query("api-key") String apiKey);

}
