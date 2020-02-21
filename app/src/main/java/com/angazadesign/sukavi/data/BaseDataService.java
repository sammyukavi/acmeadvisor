package com.angazadesign.sukavi.data;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.angazadesign.sukavi.application.Utility.getRandomQuote;

public abstract class BaseDataService<RS> implements DataService {
	
	RS restService;
	
	BaseDataService() {
		restService = RestServiceBuilder.createService(getRestServiceClass());
	}
	
	protected abstract Class<RS> getRestServiceClass();
	
	public void cancelRequests() {
		RestServiceBuilder.cancelRequests();
	}
	
	<SR> void fetchOnline(final Call<SR> request, final RestCallback<SR> restCallback) {
		
		request.enqueue(new Callback<SR>() {
			
			@Override
			public void onResponse(Call<SR> call, Response<SR> response) {
				
				if (response.isSuccessful() && restCallback != null) {
					restCallback.onCompleted(response.body());
				} else if (restCallback != null) {
					restCallback.onCompleted((SR) getRandomQuote());
				}
			}
			
			@Override
			public void onFailure(Call<SR> call, Throwable throwable) {
				if (restCallback != null) {
					restCallback.onError(throwable);
				}
			}
		});
	}
}
