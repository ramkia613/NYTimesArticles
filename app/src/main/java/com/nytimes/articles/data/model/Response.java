package com.nytimes.articles.data.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Response<T> {

    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;
    public static final int LOADING = 2;

    @NonNull
    public final int status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable error;

    public Response(@NonNull int status, @Nullable T data, Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    @Nullable
    public static <T> Response<T> success(@Nullable T data) {
        return new Response<>(SUCCESS, data, null);
    }

    @Nullable
    public static <T> Response<T> error(@Nullable T data, Throwable error) {
        return new Response<>(FAILURE, data, error);
    }

}
