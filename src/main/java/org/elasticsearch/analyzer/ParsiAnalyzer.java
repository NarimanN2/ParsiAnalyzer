package org.elasticsearch.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.DecimalDigitFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.elasticsearch.analyzer.characterfilters.DecimalPointCharFilter;
import org.elasticsearch.analyzer.characterfilters.ZeroWidthNonJoinerCharFilter;
import org.elasticsearch.analyzer.tokenfilters.PersianNormalizationFilter;
import org.elasticsearch.analyzer.tokenfilters.PersianStopFilter;

import java.io.Reader;

/**
 * Created by Nariman on 10/28/2017.
 */
public class ParsiAnalyzer extends Analyzer {

    @Override
    protected Reader initReader(String fieldName, Reader reader) {
        reader = super.initReader(fieldName, reader);
        CharFilter charFilter = new ZeroWidthNonJoinerCharFilter(reader);
        charFilter = new DecimalPointCharFilter(charFilter);
        return charFilter;
    }

    @Override
    protected TokenStreamComponents createComponents(String s) {
        final Tokenizer source = new StandardTokenizer();
        TokenStream result = new PersianStopFilter(source);
        result = new DecimalDigitFilter(result);
        result = new PersianNormalizationFilter(result);
        return new TokenStreamComponents(source, result);
    }
}
