package com.corp.tsdb.cassandra.webengine.api.rest.model;


public class NotFoundException extends ApiException {
	private int code;
	public NotFoundException (int code, String msg) {
		super(code, msg);
		this.code = code;
	}
}
