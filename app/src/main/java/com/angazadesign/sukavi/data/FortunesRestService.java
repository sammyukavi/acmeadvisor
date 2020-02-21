package com.angazadesign.sukavi.data;

import com.angazadesign.sukavi.models.Response;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FortunesRestService {
	
	@GET("fortune")
	Call<Response> getFortunes();
}
