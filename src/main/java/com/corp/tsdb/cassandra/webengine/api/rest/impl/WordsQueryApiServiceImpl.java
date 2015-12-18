package com.corp.tsdb.cassandra.webengine.api.rest.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.corp.tsdb.cassandra.webengine.api.rest.model.ErrorConcise;
import com.corp.tsdb.cassandra.webengine.api.rest.model.NotFoundException;
import com.corp.tsdb.cassandra.webengine.api.rest.model.PayloadWords;
import com.corp.tsdb.cassandra.webengine.api.rest.resources.services.WordsQueryApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JaxRSServerCodegen", date = "2015-09-15T17:27:51.281+08:00")
public class WordsQueryApiServiceImpl extends WordsQueryApiService{
	private static final Logger logger = LoggerFactory.getLogger(WordsQueryApiServiceImpl.class);
	
	@Override
	public Response queryWordsPost(PayloadWords body) throws NotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		logger.debug("WordsQueryApiServiceImpl receive words from web:{}",body.getWords());
		try {
			JSONArray result = new JSONArray();
			try {
				for(int i = 0;i < 25;i++){
					JSONObject object = new JSONObject();
					object.put("title", "Happy Happy Birthday to Me Singles Club: June - Wikipedia, the free encyclopedia".toLowerCase());
					object.put("url", "file:///Users/xuyi/sourceData/Happy_Happy_Birthday_to_Me_Singles_Club~_June_2162.html");
					object.put("body", "Happy Happy Birthday to Me Singles Club: June Happy Happy Birthday to Me Singles Club: June Happy Happy Birthday to Me Singles Club: June Happy Happy Birthday to Me Singles Club: June".toLowerCase());
					result.add(object);
				}
				Thread.sleep(5000);

			} catch (Exception ex) {

				ErrorConcise err = new ErrorConcise();
				err.setSuccess(false);
				err.setReason(ex.getMessage());

				return Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(mapper.writeValueAsString(err)).build();
			}
			return Response.ok()
					.entity(result.toString()).build();

		} catch (JsonProcessingException ex) {
			logger.debug(ex.getMessage());
		}

		JSONObject result = new JSONObject();
		result.put("sucess", false);
		result.put("reason", "JsonProcessingException");
		return Response.ok().entity(result.toString()).build();
	}

}
