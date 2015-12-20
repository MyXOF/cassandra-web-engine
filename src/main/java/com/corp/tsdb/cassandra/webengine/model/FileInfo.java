package com.corp.tsdb.cassandra.webengine.model;

import java.nio.ByteBuffer;

public class FileInfo implements Comparable<FileInfo>{
	public int docID;
	public ByteBuffer title;
	public ByteBuffer url;
	public int line;
	public ByteBuffer content;
	public double weight;
	
	public FileInfo(int docID,ByteBuffer title,ByteBuffer url,int line,ByteBuffer content,double weight){
		this.docID = docID;
		this.title = title;
		this.url = url;
		this.line = line;
		this.content = content;
		this.weight = weight;
	}
	
	public void addWeight(double otherWeight){
		this.weight += otherWeight;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileInfo other = (FileInfo) obj;
		return this.docID == other.docID;
	}
	
	@Override
	public int compareTo(FileInfo o) {
		return this.weight - o.weight > 0 ? 1 : -1;
	}
	
	
	public static void main(String[] args) {

	}



}
