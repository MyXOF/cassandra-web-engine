package com.corp.tsdb.cassandra.webengine.api.rest.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corp.tsdb.cassandra.webengine.api.rest.model.ErrorConcise;
import com.corp.tsdb.cassandra.webengine.api.rest.model.NotFoundException;
import com.corp.tsdb.cassandra.webengine.api.rest.model.PayloadExample;
import com.corp.tsdb.cassandra.webengine.api.rest.model.PayloadSwitchOnOff;
import com.corp.tsdb.cassandra.webengine.api.rest.model.ResponseStatusOnOff;
import com.corp.tsdb.cassandra.webengine.api.rest.resources.services.ExampleApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JaxRSServerCodegen", date = "2015-09-15T17:27:51.281+08:00")
public class ExampleApiServiceImpl extends ExampleApiService{
	private static final Logger logger = LoggerFactory.getLogger(ExampleApiServiceImpl.class);

	@Override
	public Response daemonStatusGet() throws NotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Receive Message Get");
		try {
			boolean status = false;
			JSONObject result = new JSONObject();
			try {			
				result.put("name", "xuyi");
				result.put("age", "22");
				result.put("gender", "male");
			} catch (Exception ex) {

				ErrorConcise err = new ErrorConcise();
				err.setSuccess(false);
				err.setReason(ex.getMessage());

				return Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(mapper.writeValueAsString(err)).build();
			}

			ResponseStatusOnOff responseStatus = new ResponseStatusOnOff();
			responseStatus.setSucess(true);
			responseStatus.setStatus(status);

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

	@Override
	public Response daemonStatusPut(PayloadSwitchOnOff body)
			throws NotFoundException {
		logger.debug("daemonStatusPut entry: {}", body.toString());

		ObjectMapper mapper = new ObjectMapper();
		logger.debug("ObjectMapper: {}", mapper);
		try {

			boolean status = false;
			try {
				if (body.getSwitch() == true) {
				} else {
				}
			} catch (Exception ex) {

				ErrorConcise err = new ErrorConcise();
				err.setSuccess(false);
				err.setReason(ex.getMessage());

				logger.debug("Exception:", ex);

				return Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(mapper.writeValueAsString(err)).build();
			}

			ResponseStatusOnOff responseStatus = new ResponseStatusOnOff();
			responseStatus.setSucess(true);
			responseStatus.setStatus(status);

			return Response.ok()
					.entity(mapper.writeValueAsString(responseStatus)).build();

		} catch (JsonProcessingException ex) {
			logger.debug(ex.getMessage());
		}

		JSONObject result = new JSONObject();
		result.put("sucess", false);
		result.put("reason", "JsonProcessingException");
		return Response.ok().entity(result.toString()).build();
	}

	@Override
	public Response daemonStatusPost(PayloadExample body)
			throws NotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Receive Message Post");
		try {
			JSONArray result = new JSONArray();
			try {
				for(int i = 0;i < 25;i++){
					JSONObject object = new JSONObject();
					object.put("title", "Happy Happy Birthday to Me Singles Club: June - Wikipedia, the free encyclopedia");
					object.put("body", "Happy Happy Birthday to Me Singles Club: June");
					result.add(object);
				}

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
