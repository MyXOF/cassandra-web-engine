package com.corp.tsdb.cassandra.webengine.daemon;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.search.vectorhighlight.BaseFragmentsBuilder;
import org.apache.lucene.search.vectorhighlight.FastVectorHighlighter;
import org.apache.lucene.search.vectorhighlight.FieldQuery;
import org.apache.lucene.search.vectorhighlight.FragListBuilder;
import org.apache.lucene.search.vectorhighlight.FragmentsBuilder;
import org.apache.lucene.search.vectorhighlight.ScoreOrderFragmentsBuilder;
import org.apache.lucene.search.vectorhighlight.SimpleFragListBuilder;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class Test {
	private String text = "China has lots of people,most of them are very poor.China is very big.China become strong now,but the poor people is also poor than other controry";

	public void highlighter() throws IOException, InvalidTokenOffsetsException {

		TermQuery termQuery = new TermQuery(new Term("field", "china"));
		TokenStream tokenStream = new SimpleAnalyzer().tokenStream("field",
				new StringReader(text));

		QueryScorer queryScorer = new QueryScorer(termQuery);
		Highlighter highlighter = new Highlighter(queryScorer);
		highlighter.setTextFragmenter(new SimpleSpanFragmenter(queryScorer));
		System.out.println(highlighter.getBestFragment(tokenStream, text));
	}

	// 使用CSS進行高亮顯示處理
	public void highlighter_CSS(String searchText) throws IOException,
			InvalidTokenOffsetsException, ParseException {

		// 創建查詢
		QueryParser queryParser = new QueryParser("field", new SimpleAnalyzer());
		Query query = queryParser.parse(searchText);

		// 自定义标注高亮文本标签
		SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter(
				"<span style=\"backgroud:red\">", "</span>");
		// 语汇单元化
		TokenStream tokenStream = new SimpleAnalyzer().tokenStream("field",
				new StringReader(text));

		// 創建QueryScoer
		QueryScorer queryScorer = new QueryScorer(query, "field");

		Highlighter highlighter = new Highlighter(htmlFormatter, queryScorer);
		highlighter.setTextFragmenter(new SimpleSpanFragmenter(queryScorer));

		System.out.println(highlighter.getBestFragments(tokenStream, text, 4,
				"..."));
	}

	// 高亮顯示搜索結果
	public void highlighter_SR(String field, String searchText)
			throws IOException, ParseException, InvalidTokenOffsetsException {

		// // 本次示例为了简便直接使用之前实验建立的索引
		// Directory directory = new SimpleFSDirectory(new File("E://MyIndex"));
		// IndexReader reader = DirectoryReader.open(directory);// 读取目录
		// IndexSearcher search = new IndexSearcher(reader);// 初始化查询组件
		// QueryParser parser = new QueryParser(Version.LUCENE_5_3_1, field,
		// new IKAnalyzer(true));
		//
		// Query query = parser.parse(searchText);
		//
		// TopDocs td = search.search(query, 10000);// 获取匹配上元素的一个docid
		// ScoreDoc[] sd = td.scoreDocs;// 加载所有的Documnet文档
		//
		// System.out.println("本次命中数据:" + sd.length);
		// QueryScorer scorer = new QueryScorer(query, "content");
		//
		// Highlighter highlighter = new Highlighter(scorer);
		// highlighter.setTextFragmenter(new SimpleSpanFragmenter(scorer));
		//
		// for (ScoreDoc scoreDoc : sd) {
		// Document document = search.doc(scoreDoc.doc);
		// String content = document.get("content");
		// TokenStream tokenStream = TokenSources.getAnyTokenStream(
		// search.getIndexReader(), scoreDoc.doc, "content", document,
		// new IKAnalyzer(true));
		// System.out.println(highlighter
		// .getBestFragment(tokenStream, content));
		// }
	}

	public static void main(String[] args) {
//		FieldType type = new FieldType(TextField.TYPE_STORED);
//		type.setStoreTermVectorOffsets(true);// 记录相对增量
//		type.setStoreTermVectorPositions(true);// 记录位置信息
//		type.setStoreTermVectors(true);// 存储向量信息
//		type.freeze();// 阻止改动信息
//		Field field = new Field("字段名", "值", type);// 示例
//		Query q = query.parse("伟大的中华民族");  
//	    TopDocs top = searcher.search(q, 100);  
//	    //QueryScorer score=new QueryScorer(q, filed);  
//	    //SimpleHTMLFormatter fors=new SimpleHTMLFormatter("<span style=\"color:red;\">", "</span>");//定制高亮标签  
//	    //Highlighter  highlighter=new Highlighter(fors,score);//高亮分析器  
//	    //FastVectorHighlighter fastHighlighter=new FastVectorHighlighter();  
//	    FragListBuilder fragListBuilder=new SimpleFragListBuilder();  
//	    //注意下面的构造函数里，使用的是颜色数组，用来支持多种颜色高亮  
//	    FragmentsBuilder fragmentsBuilder= new ScoreOrderFragmentsBuilder(BaseFragmentsBuilder.COLORED_PRE_TAGS,BaseFragmentsBuilder.COLORED_POST_TAGS);  
//	      
//	    
//	    FastVectorHighlighter fastHighlighter2=new FastVectorHighlighter(true, true, fragListBuilder, fragmentsBuilder);  
//	    FieldQuery querys=fastHighlighter2.getFieldQuery(q);//reader是传入的流  
//	      
//	    // highlighter.setMaxDocCharsToAnalyze(1);//设置高亮处理的字符个数  
//	    for(ScoreDoc sd:top.scoreDocs){  
//	       
//	        String snippt=fastHighlighter2.getBestFragment(querys, reader, sd.doc,filed,300);  
//	       
//	         if(snippt!=null){  
//	             System.out.println("高亮的片段是:"+snippt);  
//	         }  
		
	}

}
