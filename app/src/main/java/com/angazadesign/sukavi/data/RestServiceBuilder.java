package com.angazadesign.sukavi.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

import static com.angazadesign.sukavi.application.Utility.Units.*;
import static com.angazadesign.sukavi.application.Utility.getApiUrl;


public class RestServiceBuilder {
	
	private static Retrofit retrofit;
	private static OkHttpClient client;
	
	static {
		Retrofit.Builder builder = getRetrofitBuilder().baseUrl(getApiUrl());
		retrofit = builder.build();
	}
	
	private static GsonConverterFactory buildGsonConverter() {
		Gson gson = new GsonBuilder()
				.create();
		return GsonConverterFactory.create(gson);
	}
	
	private static Retrofit.Builder getRetrofitBuilder() {
		client = getSafeOkHttpClient();
		return new Retrofit.Builder()
				.addConverterFactory(buildGsonConverter())
				.client(client);
	}
	
	private static OkHttpClient.Builder getOkHttpClientBuilder() {
		Interceptor acceptHeader = chain -> {
			Request request = chain.request().newBuilder()
					.addHeader("Accept", "application/json")
					.addHeader("Content-Type", "application/json;charset=UTF-8")
					.build();
			return chain.proceed(request);
		};
		
		//add logging
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
		httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		
		return new OkHttpClient.Builder().addInterceptor(acceptHeader)
				.addInterceptor(httpLoggingInterceptor)
				.readTimeout(READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
				.connectTimeout(CONNECT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
				.retryOnConnectionFailure(false);
	}
	
	private static OkHttpClient getSafeOkHttpClient() {
		return getOkHttpClientBuilder().build();
	}
	
	static <S> S createService(Class<S> serviceClass) {
		return retrofit.create(serviceClass);
	}
	
	static void cancelRequests() {
		for (Call call : client.dispatcher().queuedCalls()) {
			call.cancel();
		}
		for (Call call : client.dispatcher().runningCalls()) {
			call.cancel();
		}
	}
}
