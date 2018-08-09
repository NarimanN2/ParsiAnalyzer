package org.elasticsearch.analyzer.tokenfilters;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

import java.util.Arrays;
import java.util.List;

public class PersianStopFilterFactory extends AbstractTokenFilterFactory {

    public static final CharArraySet PERSIAN_STOP_WORDS_SET;

    public PersianStopFilterFactory(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        super(indexSettings, name, settings);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new PersianStopFilter(tokenStream, PERSIAN_STOP_WORDS_SET);
    }

    static {
        List<String> stopWords = Arrays.asList("به", "با", "که", "از", "است", "و", "را", "این", "در");
        CharArraySet stopSet = PersianStopFilter.makeStopSet(stopWords);
        PERSIAN_STOP_WORDS_SET = CharArraySet.unmodifiableSet(stopSet);
    }

}
