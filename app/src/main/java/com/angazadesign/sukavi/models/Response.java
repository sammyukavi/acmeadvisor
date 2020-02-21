package com.angazadesign.sukavi.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response{

	@SerializedName("fortune")
	private List<String> fortunes;

	public void setFortunes(List<String> fortunes){
		this.fortunes = fortunes;
	}

	public List<String> getFortunes(){
		return fortunes;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"fortune = '" + fortunes + '\'' +
			"}";
		}
}