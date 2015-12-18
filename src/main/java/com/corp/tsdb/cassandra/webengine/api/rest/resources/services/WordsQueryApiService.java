package com.corp.tsdb.cassandra.webengine.api.rest.resources.services;

import javax.ws.rs.core.Response;

import com.corp.tsdb.cassandra.webengine.api.rest.model.NotFoundException;
import com.corp.tsdb.cassandra.webengine.api.rest.model.PayloadWords;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JaxRSServerCodegen", date = "2015-09-15T17:27:51.281+08:00")
public abstract class WordsQueryApiService {
	public abstract Response queryWordsPost(PayloadWords body) throws NotFoundException;;
}
