package com.angazadesign.sukavi.data;

public interface DataService {
	
	interface RestCallback<SR> {
		
		void onCompleted(SR response);
		
		void onError(Throwable throwable);
	}
}
