package org.elasticsearch.analyzer.tokenfilters;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.FilteringTokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nariman on 10/29/2017.
 */
public final class PersianStopFilter extends FilteringTokenFilter {

    private final CharArraySet stopWords;
    private final CharTermAttribute termAttribute = addAttribute(CharTermAttribute.class);

    public PersianStopFilter(TokenStream in, CharArraySet stopWords) {
        super(in);
        this.stopWords = stopWords;
    }

    public static CharArraySet makeStopSet(String... stopWords) {
        return makeStopSet(Arrays.asList(stopWords));
    }

    public static CharArraySet makeStopSet(List stopWords) {
        CharArraySet stopSet = new CharArraySet(stopWords.size(), true);
        stopSet.addAll(stopWords);
        return stopSet;
    }

    @Override
    protected boolean accept() throws IOException {
        String token = new String(termAttribute.buffer(), 0, termAttribute.length()).trim();
        return !stopWords.contains(token);
    }
}
