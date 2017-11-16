package org.elasticsearch.analyzer.characterfilters;

import org.apache.lucene.analysis.pattern.PatternReplaceCharFilter;

import java.io.Reader;
import java.util.regex.Pattern;

/**
 * Created by Nariman on 11/15/2017.
 */
public class ZeroWidthNonJoinerCharFilter extends PatternReplaceCharFilter {

    private static final String zwnjPattern1 = "(?<=[^ ]\\u0647) (?=\\u06CC)";
    private static final String zwnjPattern2 = "(?<=(\\u0645\\u06CC|\\u0646\\u0645\\u06CC)) (?=[^ ])";
    private static final String zwnjPattern3 = "(?<=[^ ]) (?=(\\u0647\\u0627|\\u0647\\u0627\\u06CC|\\u062A\\u0631|\\u062A\\u0631\\u06CC|\\u062A\\u0631\\u06CC\\u0646))";
    private static final String zwnjPattern4 = "(?<=[^ ]\\u0647) (?=(\\u0627\\u0645|\\u0627\\u062A|\\u0627\\u0634|\\u0627\\u06CC))";

    private ZeroWidthNonJoinerCharFilter(Pattern pattern, String replacement, Reader in) {
        super(pattern, replacement, in);
    }

    public ZeroWidthNonJoinerCharFilter(Reader in) {
        this(Pattern.compile(zwnjPattern1 + "|" + zwnjPattern2 + "|" + zwnjPattern3 + "|" + zwnjPattern4), "\u200C", in);
    }
}
