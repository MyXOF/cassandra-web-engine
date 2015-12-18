package com.corp.tsdb.cassandra.webengine.api.rest.model;

public class ApiException extends Exception{
	private int code;
	public ApiException (int code, String msg) {
		super(msg);
		this.code = code;
	}
}
