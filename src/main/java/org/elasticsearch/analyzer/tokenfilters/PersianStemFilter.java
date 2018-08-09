package org.elasticsearch.analyzer.tokenfilters;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nariman on 10/28/2017.
 */
public final class PersianStemFilter extends TokenFilter {

    private List<String> suffixes;
    private final CharTermAttribute termAttribute = addAttribute(CharTermAttribute.class);

    public PersianStemFilter(TokenStream in) {
        super(in);
        suffixes = new ArrayList<>();
        suffixes.add("یی");
        suffixes.add("ی");
        suffixes.add("ها");
        suffixes.add("تر");
        suffixes.add("ترین");
        suffixes.add("ان");
        suffixes.add("ات");
        suffixes.add("\u200c");
    }

    @Override
    public boolean incrementToken() throws IOException {

        if (input.incrementToken()) {
            String token = new String(termAttribute.buffer(), 0, termAttribute.length()).trim();

            if (token.length() > 0) {

                for (String suffix : suffixes) {
                    if (token.endsWith(suffix))
                        token = token.substring(0, token.length() - suffix.length());
                }

                termAttribute.setEmpty();
                termAttribute.append(token);
            }

            return true;
        }

        return false;
    }
}
