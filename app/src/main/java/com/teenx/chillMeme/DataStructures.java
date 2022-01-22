package com.teenx.chillMeme;

public class DataStructures {
	String url;
	String AuthorName;
	DataStructures(String url,String AuthorName){
		this.url = url;
		this.AuthorName = AuthorName;
	}
	public String getUrl(){
		return this.url;
	}
	public String getAuthorName(){
		return this.AuthorName;
	}
}