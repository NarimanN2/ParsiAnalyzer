package org.elasticsearch.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

/**
 * Created by Nariman on 10/28/2017.
 */
public class ParsiAnalyzerProvider extends AbstractIndexAnalyzerProvider<ParsiAnalyzer> {

    private final ParsiAnalyzer analyzer;

    public ParsiAnalyzerProvider(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        super(indexSettings, name, settings);
        analyzer = new ParsiAnalyzer();
    }

    @Override
    public ParsiAnalyzer get() {
        return this.analyzer;
    }
}
