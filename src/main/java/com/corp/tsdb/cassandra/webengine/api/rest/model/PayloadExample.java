package com.corp.tsdb.cassandra.webengine.api.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JaxRSServerCodegen", date = "2015-09-06T14:26:23.511+08:00")
public class PayloadExample {
	private String foo;
	private String bar;
	
	@JsonProperty("foo")
	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}
	
	@JsonProperty("bar")
	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResponseStatusOnOff {\n");

		sb.append("  foo: ").append(foo).append("\n");
		sb.append("  bar: ").append(bar).append("\n");
		sb.append("}\n");
		return sb.toString();
	}
}
