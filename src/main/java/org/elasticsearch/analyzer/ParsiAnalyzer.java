package org.elasticsearch.analyzer;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.DecimalDigitFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.elasticsearch.analyzer.characterfilters.ZeroWidthNonJoinerCharFilter;
import org.elasticsearch.analyzer.tokenfilters.PersianNormalizationFilter;
import org.elasticsearch.analyzer.tokenfilters.PersianStemFilter;
import org.elasticsearch.analyzer.tokenfilters.PersianStopFilter;

import java.io.Reader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nariman on 10/28/2017.
 */
public class ParsiAnalyzer extends Analyzer {

    public static final CharArraySet PERSIAN_STOP_WORDS_SET;

    @Override
    protected Reader initReader(String fieldName, Reader reader) {
        reader = super.initReader(fieldName, reader);
        CharFilter charFilter = new ZeroWidthNonJoinerCharFilter(reader);
        return charFilter;
    }

    @Override
    protected TokenStreamComponents createComponents(String s) {
        final Tokenizer source = new StandardTokenizer();
        TokenStream result = new PersianStopFilter(source, PERSIAN_STOP_WORDS_SET);
        result = new DecimalDigitFilter(result);
        result = new PersianNormalizationFilter(result);
        result = new PersianStemFilter(result);
        return new TokenStreamComponents(source, result);
    }

    static {
        List<String> stopWords = Arrays.asList("به", "با", "که", "از", "است", "و", "را", "این", "در");
        CharArraySet stopSet = PersianStopFilter.makeStopSet(stopWords);
        PERSIAN_STOP_WORDS_SET = CharArraySet.unmodifiableSet(stopSet);
    }

}
