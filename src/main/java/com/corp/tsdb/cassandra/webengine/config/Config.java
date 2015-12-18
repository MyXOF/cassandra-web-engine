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
	public String source_root_path;
	
	private void readConfig(){
		stopWords = new HashSet<String>();
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Config.class.getResourceAsStream(STOP_WORDS_PATH)))) {
			String line = "";
			while((line = bufferedReader.readLine()) != null){
				stopWords.add(line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			logger.error("Config fail to read stop words from {}",STOP_WORDS_PATH,e);
		}
		
		Properties prop = new Properties();
		try(InputStream in = Config.class.getResourceAsStream(CONF_FILE_PATH)){
			prop.load(in);
			source_root_path = prop.getProperty("file_root_path");
		} catch (Exception e) {
			logger.error("Config fail to read stop words from {}",STOP_WORDS_PATH,e);
		}
	}
	
	public static void main(String[] args) {

	}
}
