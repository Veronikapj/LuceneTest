package sample.book.lucene;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.KeywordAttribute;

public class AnalyzerUtils {

	public static void displayTokens(Analyzer analyzer, String text) throws IOException{
		displayTokens(analyzer.tokenStream("contents", new StringReader(text)));
	}
	
	public static void displayTokens(TokenStream stream) throws IOException{
		CharTermAttribute term = stream.addAttribute(CharTermAttribute.class);
		stream.reset();
		while(stream.incrementToken()) {
			System.out.print("[" + term.toString() +"]");
		}
		System.out.println("");
		stream.close();
		
		/**
		 * 책에 있는 코드
		 * TermAttribute term = stream.addAttribute(TermAttribute.clss);
		 * 
		 * TermAttribute를 현재 루씬에서 찾을 수 없어서 CharTermAttribute로 바꿔서 사용했다.
		 * stream.reset()/close() 를 추가로 넣었다.
		 */
		
	}
	
}
