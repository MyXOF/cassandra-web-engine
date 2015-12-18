package com.corp.tsdb.cassandra.webengine.api.rest.resources.factories;

import com.corp.tsdb.cassandra.webengine.api.rest.impl.WordsQueryApiServiceImpl;
import com.corp.tsdb.cassandra.webengine.api.rest.resources.services.WordsQueryApiService;

public class WordsQueryApiServiceFactory {
	private static final WordsQueryApiService service = new WordsQueryApiServiceImpl();
	
	public static WordsQueryApiService getWordsQueryApi(){
		return service;
	}
}
