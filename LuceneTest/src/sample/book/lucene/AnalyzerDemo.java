package sample.book.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class AnalyzerDemo {

	private static final String[] examples = {
		"The quick brown for jumped over the laze dog.",
		"XY&Z Corporation - xyz@example.com"
	};
	
	private static final Analyzer[] analyzers = new Analyzer[] {
			new WhitespaceAnalyzer(),
			new SimpleAnalyzer(),
			new StopAnalyzer(),
			new StandardAnalyzer()
	};
	
	public static void main(String[] args) throws IOException{
		String[] strings = examples;
		
		if(args.length > 0) {
			strings = args;
		}
		
		for(String text : strings) {
			analyze(text);
		}
	}
	
	private static void analyze(String text) throws IOException {
		System.out.println("Analyzing :" + text);
		for(Analyzer analyzer : analyzers) {
			String name = analyzer.getClass().getSimpleName();
			System.out.println("name : " + name);
			AnalyzerUtils.displayTokens(analyzer, text);
			
		}
	}
	

}
