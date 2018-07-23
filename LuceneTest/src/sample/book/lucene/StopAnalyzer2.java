/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.book.lucene;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.WordlistLoader;
import org.apache.lucene.analysis.core.LowerCaseTokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

/**
 * Filters {@link LetterTokenizer} with {@link LowerCaseFilter} and
 * {@link StopFilter}. 현재 코어의 StopAnalyzer 소스이다.
 */
public final class StopAnalyzer2 extends StopwordAnalyzerBase {

	/**
	 * An unmodifiable set containing some common English words that are not usually
	 * useful for searching.
	 */
	public static final CharArraySet ENGLISH_STOP_WORDS_SET = StandardAnalyzer.ENGLISH_STOP_WORDS_SET;

	/**
	 * Builds an analyzer which removes words in {@link #ENGLISH_STOP_WORDS_SET}.
	 */
	public StopAnalyzer2() {
		this(ENGLISH_STOP_WORDS_SET);
	}

	/**
	 * Builds an analyzer with the stop words from the given set.
	 * 
	 * @param stopWords
	 *            Set of stop words
	 */
	public StopAnalyzer2(CharArraySet stopWords) {
		super(stopWords);
	}

	/**
	 * Builds an analyzer with the stop words from the given path.
	 * 
	 * @see WordlistLoader#getWordSet(Reader)
	 * @param stopwordsFile
	 *            File to load stop words from
	 */
	public StopAnalyzer2(Path stopwordsFile) throws IOException {
		this(loadStopwordSet(stopwordsFile));
	}

	/**
	 * Builds an analyzer with the stop words from the given reader.
	 * 
	 * @see WordlistLoader#getWordSet(Reader)
	 * @param stopwords
	 *            Reader to load stop words from
	 */
	public StopAnalyzer2(Reader stopwords) throws IOException {
		this(loadStopwordSet(stopwords));
	}

	/**
	 * Creates {@link org.apache.lucene.analysis.Analyzer.TokenStreamComponents}
	 * used to tokenize all the text in the provided {@link Reader}.
	 * 
	 * @return {@link org.apache.lucene.analysis.Analyzer.TokenStreamComponents}
	 *         built from a {@link LowerCaseTokenizer} filtered with
	 *         {@link StopFilter}
	 *         
	 * Analyzer를 extend 할때 오버라이드 하게끔 나온다.
	 * TokenStreamComponents 를 사용해도 lowercase로 변환한 다음(source) 
	 * StopFilter (result) 로 넘겨주어야 하는 것은 동일한 것 같다.
	 *  
	 * TokenStreamComponents
	 * @param source : the analyzer's tokenizer
     * @param result : the analyzer's resulting token stream
	 */
	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		final Tokenizer source = new LowerCaseTokenizer();
		return new TokenStreamComponents(source, new StopFilter(source, stopwords));
	}
	
	/**
	 * 책에 나온 이전 소스.
	public TokenStream tokenStream(String fieldName, Reader reader) {
		TokenFilter에 따라 다르지만 분석기 체인을 연결하는 순서가 중요한 경우가 있다.
		이전에 StopFilter는 대소문자를 구분해서 확인했기 때문에 소문자로 변경해 결과를 넘겨주어야 해서 다음과 같이 해야 했다.
		return StopFilter(true, new LowerCaseFilter(new LetterTokenizer(reader)), stopwords);
		따라서 다음과 같이 순서를 바꾸는 경우 제대로 동작하지 않을 수 있었다.
		return new LowerCaseFilter(new StopFilter(true, new LetterTokenizer(reader), stopwords));
		
	}
	**/

	@Override
	protected TokenStream normalize(String fieldName, TokenStream in) {
		return new LowerCaseFilter(in);
	}

}
