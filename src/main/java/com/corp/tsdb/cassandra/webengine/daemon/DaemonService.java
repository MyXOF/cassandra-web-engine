package com.corp.tsdb.cassandra.webengine.daemon;

import net.sf.json.JSONArray;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corp.tsdb.cassandra.webengine.config.Config;

public class DaemonService {
	private static final Logger logger = LoggerFactory.getLogger(DaemonService.class);
	
	private static final DaemonService service = new DaemonService();
	public static final DaemonService getInstance(){return service;}
	private DaemonService(){}
	
	public JSONArray queryContent(String message){
		JSONArray result = new JSONArray();

		Set<String> queryWords = filterMessage(message);
		if(queryWords == null || queryWords.size() == 0){
			return result;
		}
		
		
		
		
		return result;
	}
	
	private Set<String> filterMessage(String message){
		String values[] = message.split("\\s");
		Set<String> stopWords = Config.getInstance().stopWords;
		Set<String> queryWords = new HashSet<String>();
		
		if(values == null || values.length == 0){
			return queryWords;
		}
		
		for(String word : values){
			String _word = word.toLowerCase();
			if(stopWords.contains(_word)){
				continue;
			}
			queryWords.add(_word);
		}
		
		return queryWords;
	}
	
	public static void main(String[] args) {
		String[] a = "doge cat duck".split("\\s");
		for(String b : a){
			System.out.println(b);
		}

	}

}
