package com.corp.tsdb.cassandra.webengine.api.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.corp.tsdb.cassandra.webengine.api.rest.model.ErrorConcise;
import com.corp.tsdb.cassandra.webengine.api.rest.model.NotFoundException;
import com.corp.tsdb.cassandra.webengine.api.rest.model.PayloadWords;
import com.corp.tsdb.cassandra.webengine.api.rest.resources.factories.WordsQueryApiServiceFactory;
import com.corp.tsdb.cassandra.webengine.api.rest.resources.services.WordsQueryApiService;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/query")
@Produces({ "application/json" })
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JaxRSServerCodegen", date = "2015-09-15T17:27:51.281+08:00")
public class WordsQueryApi {
	private final WordsQueryApiService delegate = WordsQueryApiServiceFactory.getWordsQueryApi();
	
	@POST
	@Path("retrieval")
	@Consumes({ "application/json", "application/xml" ,"application/x-www-form-urlencoded","text/plain"})
	@Produces({ "application/json", "application/xml" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),

			@ApiResponse(code = 400, message = "Bad Request"),

			@ApiResponse(code = 415, message = "Bad Content Type"),

			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorConcise.class) })
	public Response daemonStatusPost(PayloadWords body) throws NotFoundException {
		return delegate.queryWordsPost(body);
	}
}
