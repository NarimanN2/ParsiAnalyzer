package org.elasticsearch.analyzer.characterfilters;

import org.apache.lucene.analysis.pattern.PatternReplaceCharFilter;

import java.io.Reader;
import java.util.regex.Pattern;

/**
 * Created by Nariman on 11/15/2017.
 */
public class ZeroWidthNonJoinerCharFilter extends PatternReplaceCharFilter {

    private static final String NonStandardZWNJPattern = "[\\u200B\\u200D\\u200E\\u200F\\u001F\\u00AC]";
    private static final String YehPattern = "(?<=[^ ]\\u0647) (?=\\u06CC|\\u064A)";
    private static final String presentContinuousPattern = "(?<=([^\\p{L}]\\u0645\\u06CC|[^\\p{L}]\\u0646\\u0645\\u06CC)) (?=[^ ])";
    private static final String commonSuffixesPattern = "(?<=[^ ]) (?=(\\u0647\\u0627|\\u0647\\u0627\\u06CC|\\u0647\\u0627\\u064A|\\u062A\\u0631|\\u062A\\u0631\\u06CC|\\u062A\\u0631\\u06CC\\u0646|\\u062A\\u0631\\u064A|\\u062A\\u0631\\u064A\\u0646))";

    private ZeroWidthNonJoinerCharFilter(Pattern pattern, String replacement, Reader in) {
        super(pattern, replacement, in);
    }

    public ZeroWidthNonJoinerCharFilter(Reader in) {
        this(Pattern.compile(NonStandardZWNJPattern + "|" + YehPattern + "|" + presentContinuousPattern + "|" + commonSuffixesPattern), "\u200C", in);
    }
}
