package com.corp.tsdb.cassandra.webengine.daemon;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corp.tsdb.cassandra.webengine.cassandra.CassandraCluster;
import com.corp.tsdb.cassandra.webengine.config.Config;
import com.corp.tsdb.cassandra.webengine.model.FileInfo;
import com.corp.tsdb.cassandra.webengine.utils.Gzip;

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
		logger.debug("Query words {}",queryWords.toString());
		result = queryWords(queryWords);
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
	
	
	private JSONArray queryWords(Set<String> queryWords){
		CassandraCluster cluster = CassandraCluster.getInstance();
		String keyspace = Config.getInstance().cassandra_keyspace;
		List<FileInfo> result = cluster.selectResult(keyspace, queryWords);
		return transfer(result);
	}
	
	private JSONArray transfer(List<FileInfo> infoList){
		JSONArray result = new JSONArray();
		String root_path = Config.getInstance().file_root_path;
		for(FileInfo info : infoList){
			JSONObject object = new JSONObject();
			try {
				object.put("title", Gzip.decompress(info.title.array()));
				object.put("url", String.format("%s/%s", root_path,Gzip.decompress(info.url.array())));
				object.put("body", Gzip.decompress(info.content.array()));
			} catch (IOException e) {
				logger.error("DaemonService : failed to transfer data for file {}",info.docID,e);
				continue;
			}
			result.add(object);
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
