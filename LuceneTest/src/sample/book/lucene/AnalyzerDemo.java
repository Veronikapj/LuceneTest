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
			// 공백 문자를 기준으로 토큰을 분리한다.
			new WhitespaceAnalyzer(),
			// 숫자나 공백, 기호등으로 토큰을 분리하고, 글자는 모두 소문자로 변경한다.
			new SimpleAnalyzer(),
			// 숫자나 공백, 기호 등으로 토큰을 분리하고, 글자는 모두 소문자로 변경한 후, 불용어를 제거한다.
			// 불용어 : 너무 흔해 거의 모든 문서에 다 들어 있어서 문서를 구분하는 의미가 없다고 생각하는 단어.
			new StopAnalyzer(),
			// 문법을 기반으로 토큰을 분리하는 고급문석기다. 이메일, 약어, 한중일 글자, 일반 알파벳 글자 등을 인식하고
			// 토큰으로 분리한다. 알파벳은 모두 소문자로 변경하고, 불용어도 제거한다.
			new StandardAnalyzer()
			// Keyword Analyzer : 필드의 전체 원문을 하나의 토큰으로 처리한다.
	};
	
	public static void main(String[] args) throws IOException{
		String[] strings = examples;
		
		if(args.length > 0) {
			strings = args;
		}
		
		for(String text : strings) {
			analyze(text);
		}
		
		AnalyzerUtils.displayTokensWithFullDetails(new SimpleAnalyzer(), 
				"The quick brown for jumped over the laze dog.");
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
