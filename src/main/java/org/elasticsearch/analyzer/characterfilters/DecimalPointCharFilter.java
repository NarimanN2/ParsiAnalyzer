package org.elasticsearch.analyzer.characterfilters;

import org.apache.lucene.analysis.pattern.PatternReplaceCharFilter;

import java.io.Reader;
import java.util.regex.Pattern;

/**
 * Created by Nariman on 11/15/2017.
 */
public class DecimalPointCharFilter extends PatternReplaceCharFilter {

    private static final String decimalPointPattern = "(?<=\\p{N}+)/(?=\\p{N}+)";

    private DecimalPointCharFilter(Pattern pattern, String replacement, Reader in) {
        super(pattern, replacement, in);
    }

    public DecimalPointCharFilter(Reader in) {
        this(Pattern.compile(decimalPointPattern), ".", in);
    }
}
