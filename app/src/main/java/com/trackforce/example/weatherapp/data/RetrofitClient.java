package com.trackforce.example.weatherapp.data;

import com.google.gson.Gson;
import com.trackforce.example.weatherapp.BuildConfig;
import com.trackforce.example.weatherapp.config.Config;

import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit getRetrofit(Gson gson) {

        // Create Header Interceptor
        Interceptor headerInterceptor = chain -> {
            Request original = chain.request();

            HttpUrl url = original.url().newBuilder()
                    .addQueryParameter("appid", BuildConfig.WEATHER_APP_ID)
                    .build();

            Request request = original.newBuilder()
                    .url(url)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        };

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .callTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(logging);
        }

        clientBuilder.addInterceptor(headerInterceptor);

        OkHttpClient client = clientBuilder.build();

        return new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
