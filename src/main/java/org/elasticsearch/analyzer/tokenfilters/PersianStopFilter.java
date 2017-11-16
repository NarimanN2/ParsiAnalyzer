package org.elasticsearch.analyzer.tokenfilters;

import org.apache.lucene.analysis.FilteringTokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nariman on 10/29/2017.
 */
public final class PersianStopFilter extends FilteringTokenFilter {

    private Set<String> stopWords;
    private final CharTermAttribute termAttribute = addAttribute(CharTermAttribute.class);

    public PersianStopFilter(TokenStream in) {
        super(in);
        stopWords = new HashSet<>();
        stopWords.add("از");
        stopWords.add("که");
        stopWords.add("با");
        stopWords.add("و");
        stopWords.add("به");
        stopWords.add("را");
        stopWords.add("برای");
        stopWords.add("تا");
        stopWords.add("یا");
        stopWords.add("اما");
        stopWords.add("بر");
        stopWords.add("هر");
        stopWords.add("اگر");
        stopWords.add("آن");
        stopWords.add("این");
        stopWords.add("آنها");
        stopWords.add("آنان");
    }

    @Override
    protected boolean accept() throws IOException {
        String token = new String(termAttribute.buffer(), 0, termAttribute.length()).trim();
        return !stopWords.contains(token);
    }
}
