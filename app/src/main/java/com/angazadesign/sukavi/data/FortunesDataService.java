package com.angazadesign.sukavi.data;


import com.angazadesign.sukavi.models.Response;

public class FortunesDataService extends BaseDataService<FortunesRestService> {
	
	@Override
	protected Class<FortunesRestService> getRestServiceClass() {
		return FortunesRestService.class;
	}
	
	
	public void getFortunes(DataService.RestCallback<Response> restCallback) {
		fetchOnline(restService.getFortunes(), restCallback);
	}
}
