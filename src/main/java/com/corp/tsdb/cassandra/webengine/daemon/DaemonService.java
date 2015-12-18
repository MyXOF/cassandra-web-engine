package com.corp.tsdb.cassandra.webengine.daemon;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaemonService {
	private static final Logger logger = LoggerFactory.getLogger(DaemonService.class);
	
	private static final DaemonService service = new DaemonService();
	public static final DaemonService getInstance(){return service;}
	private DaemonService(){}
	
	public JSONArray queryContent(String message){
		JSONArray result = new JSONArray();
		String values[] = message.split("\\s");
		
		if(values == null || values.length == 0){
			return result;
		}
		
		
		return result;
	}
	
	public static void main(String[] args) {
		String[] a = "doge cat duck".split("\\s");
		for(String b : a){
			System.out.println(b);
		}

	}

}
