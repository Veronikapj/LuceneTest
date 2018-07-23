package sample.book.lucene;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.KeywordAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

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
	
	public static void displayTokensWithFullDetails(Analyzer analyzer, String text) throws IOException{
		TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));
		stream.reset();
		//토큰의 텍스트.
		CharTermAttribute term  = stream.addAttribute(CharTermAttribute.class);
		//위치 증가 값.
		PositionIncrementAttribute posIncr = stream.addAttribute(PositionIncrementAttribute.class);
		//원문에서 토큰 문자열의 시작과 끝 지점.
		OffsetAttribute offset = stream.addAttribute(OffsetAttribute.class);
		//토큰의 종류 (기본 값은 word)
		TypeAttribute type = stream.addAttribute(TypeAttribute.class);
		// FlagAttribute : 추가로 지정할 수 있는 비트 플래그
		// PayloadAttribute : 토큰별 byte[] 적재공간.
		int position = 0;
		
		while(stream.incrementToken()) {
			int increment = posIncr.getPositionIncrement();
			
			if(increment > 0) {
				position = position + increment;
				
				System.out.print("[" + term.toString() + " : "
						+ offset.startOffset() + " -> "
						+ offset.endOffset() + " : " 
						+ type.type() + " ]");
			}
			System.out.println();
		}
		stream.close();
	}
}
