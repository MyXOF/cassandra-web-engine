package com.corp.tsdb.cassandra.webengine.cassandra;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corp.tsdb.cassandra.webengine.config.Config;
import com.corp.tsdb.cassandra.webengine.model.FileInfo;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraCluster {
	private static Logger logger = LoggerFactory.getLogger(CassandraCluster.class);

	public static CassandraCluster getInstance() {
		if (myCluster == null)
			myCluster = new CassandraCluster();
		return myCluster;
	}

	protected static CassandraCluster myCluster;

	private String[] nodes;
	// one cluster for per phisical
	private Cluster cluster;
	// one session for per ks
	public Session session;

//	private static final String createWordCfCql = "CREATE TABLE IF NOT EXISTS %s.%s (" + "DocID int," + "Weight double,"
//			+ "Line int," + "PRIMARY KEY(DocID)" + ");";
//
//	private static final String createDocCFCql = "CREATE TABLE IF NOT EXISTS %s.%s (" + "DocID int," + "File blob,"
//			+ "Title blob," + "PRIMARY KEY(DocID)" + ");";
//
	private static final String DOC_CF = "doc_document";
//
//	private static final String createSentenceCFCql = "CREATE TABLE IF NOT EXISTS %s.%s (" + "Line int,"
//			+ "Content blob," + "PRIMARY KEY(Line)" + ");";

	private static String selectWordCql = "select DocID,Weight,Line,File,Title"
																	 + " from %s.%s natural join %s.%s" ;
	
	private static String selectSentenceCql = "select Content from %s.%s where Line=%d;";

	private CassandraCluster() {
		Config config = Config.getInstance();
		nodes = config.cassandra_nodes.split(",");
		connect(nodes);
	}

	public Session getSession() {
		return session;
	}

	public void connect(String[] nodes) {
		if (nodes == null || nodes.length == 0) {
			logger.warn("CassandraCluster : no ip node to init");
			return;

		}
		List<InetAddress> addresses = new ArrayList<InetAddress>();
		for (String node : nodes) {
			try {
				addresses.add(InetAddress.getByName(node));
			} catch (UnknownHostException e) {
				logger.error(e.getMessage());
			}
		}
		cluster = Cluster.builder().addContactPoints(addresses).build();
		logger.info("CassandraCluster : Try connect cassandra");
		try {
			Metadata metadata = cluster.getMetadata();
			logger.info("CassandraCluster: Connected to cluster");
			for (Host host : metadata.getAllHosts()) {
				logger.info("Datatacenter: {}; Host: {}; Rack: {}",
						new Object[] { host.getDatacenter(), host.getAddress().toString(), host.getRack() });
			}
			session = cluster.connect();
		} catch (Exception e) {
			logger.error("CassandraCluster : failed to connect to cluster", e);
		}
	}

	public void close() {
		if (session != null) {
			session.close();
			session = null;
		}
		if (cluster != null) {
			cluster.close();
			cluster = null;
		}
		myCluster = null;
	}

	public List<FileInfo> selectResult(String ks,Set<String> cfList) {
		ResultSet rSet = null;
		List<FileInfo> result = null;
		Map<Integer, FileInfo> infoMap = new HashMap<Integer,FileInfo>();
		if(cfList == null || cfList.size() == 0){
			return result;
		}
		for(String cf : cfList){
			rSet = session.execute(String.format(selectWordCql, ks, "w_"+cf,ks,DOC_CF));
			if(rSet != null){
				for(Row row : rSet.all()){
					FileInfo info = new FileInfo(row.getInt("DocID"),
																			  row.getBytes("Title"), 
																			  row.getBytes("File"), 
																			  row.getInt("Line"), 
																			  null,
																			  row.getDouble("Weight"));
					if(infoMap.containsKey(info.docID)){
						infoMap.get(info.docID).addWeight(info.weight);
					}
					else{
						ByteBuffer content = selectContent(ks, "s_"+info.docID,info.line);
						if(content == null){
							logger.warn("CassandraCluster cannot get content for DocID {}",info.docID);
							continue;
						}
						info.content = content;
						infoMap.put(info.docID, info);
					}
				}
			}
		}
		
		result = new ArrayList<FileInfo>(infoMap.values());
		Collections.sort(result);
		return result;
	}
	
	public ByteBuffer selectContent(String ks,String cf,int line){
		ResultSet rSet = null;
		rSet = session.execute(String.format(selectSentenceCql, ks,cf,line));
		ByteBuffer content = null;
		if(rSet != null){
			for(Row row : rSet.all()){
				content = row.getBytes("Content");
				break;
			}
		}
		return content;
	}
	
}
