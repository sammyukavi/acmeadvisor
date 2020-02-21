package com.angazadesign.sukavi.application;

import com.angazadesign.sukavi.models.Response;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utility {
	
	public static String getApiUrl() {
		return "http://api.acme.international/";
	}
	
	public static class Units {
		public static final int READ_TIME_OUT_SECONDS = 5;
		public static final int CONNECT_TIME_OUT_SECONDS = 5;
	}
	
	public static Response getRandomQuote(){
		List<String> quotes = new ArrayList<>();
		quotes.add("a penny saved is a penny earned   ");
		quotes.add("life is short, smile while you have teeth   ");
		quotes.add("people say nothing is impossible and I do nothing all day   ");
		quotes.add("doing nothing is hard, you never know when you're done   ");
		quotes.add("your life can't fall apart if you never had it together!   ");
		
		Response response = new Response();
		List<String> fortunes = new ArrayList<>();
		fortunes.add(quotes.get(generateRandomIndex(0,4)));
		response.setFortunes(fortunes);
		return  response;
	}
	
	private static int generateRandomIndex(int min, int max) {
		SecureRandom rand = new SecureRandom();
		rand.setSeed(new Date().getTime());
		return rand.nextInt((max - min) + 1) + min;
	}
}
