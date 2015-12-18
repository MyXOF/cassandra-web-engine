package com.corp.tsdb.cassandra.webengine.api.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;

@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JaxRSServerCodegen", date = "2015-09-06T14:26:23.511+08:00")
public class PayloadWords {
	private String words = "";

	@JsonProperty("words")
	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PayloadWords {\n");
		sb.append("  words: ").append(words).append("\n");
		sb.append("}\n");
		return sb.toString();
	}
}
