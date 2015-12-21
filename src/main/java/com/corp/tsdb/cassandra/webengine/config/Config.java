package com.corp.tsdb.cassandra.webengine.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	private static final Logger logger = LoggerFactory.getLogger(Config.class);
	private static final Config config = new Config();	
	public static Config getInstance(){
		return config;
	}	
	private Config(){
		readConfig();
	}
	
	private final String STOP_WORDS_PATH = "/stopWordsQuery.txt";
	public Set<String> stopWords;
	private final String CONF_FILE_PATH = "/web-engine-config.properties";
	public String file_root_path;
	
	public String cassandra_nodes = "127.0.0.1";
	public int sql_cassandra_port = 9042;
//	public String cassandra_keyspace = "test1";
	public String cassandra_keyspace_word = "word";
	public String cassandra_keyspace_sentence = "sentence";
	public String cassandra_partition_strategy = "SimpleStrategy";
	public int cassandra_replica_factor = 1;
	public String storage_engine = "cassandra";
	public String hottable_status = "HotIndexStatus.f";
	
	private void readConfig(){
		stopWords = new HashSet<String>();
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Config.class.getResourceAsStream(STOP_WORDS_PATH)))) {
			String line = "";
			while((line = bufferedReader.readLine()) != null){
				stopWords.add(line.toLowerCase());
			}
			bufferedReader.close();
		} catch (Exception e) {
			logger.error("Config fail to read stop words from {}",STOP_WORDS_PATH,e);
		}
		
		Properties prop = new Properties();
		try(InputStream in = Config.class.getResourceAsStream(CONF_FILE_PATH)){
			prop.load(in);
			file_root_path = prop.getProperty("file_root_path");
			
			cassandra_nodes = prop.getProperty("cassandra_nodes");
			sql_cassandra_port = Integer.parseInt(prop.getProperty("sql_cassandra_port"));
//			cassandra_keyspace = prop.getProperty("cassandra_keyspace");
			cassandra_keyspace_word = prop.getProperty("cassandra_keyspace_word");
			cassandra_keyspace_sentence = prop.getProperty("cassandra_keyspace_sentence");
			cassandra_partition_strategy = prop.getProperty("cassandra_partition_strategy");
			cassandra_replica_factor = Integer.parseInt(prop.getProperty("cassandra_replica_factor"));
			storage_engine = prop.getProperty("storage_engine");
			hottable_status = prop.getProperty("hottable_status");
			
			in.close();
			
		} catch (Exception e) {
			logger.error("Config fail to read stop words from {}",STOP_WORDS_PATH,e);
		}
	}
	
	public static void main(String[] args) {

	}
}
