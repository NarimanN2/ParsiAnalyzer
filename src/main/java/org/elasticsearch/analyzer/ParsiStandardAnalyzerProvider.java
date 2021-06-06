package org.elasticsearch.analyzer;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

/**
 * Created by Nariman on 10/28/2017.
 */
public class ParsiStandardAnalyzerProvider extends AbstractIndexAnalyzerProvider<ParsiStandardAnalyzer> {

    private final ParsiStandardAnalyzer analyzer;

    public ParsiStandardAnalyzerProvider(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        super(indexSettings, name, settings);
        analyzer = new ParsiStandardAnalyzer();
    }

    @Override
    public ParsiStandardAnalyzer get() {
        return this.analyzer;
    }
}
