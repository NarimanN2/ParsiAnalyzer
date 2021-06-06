package org.elasticsearch.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.analyzer.characterfilters.ZeroWidthNonJoinerCharFilterFactory;
import org.elasticsearch.analyzer.tokenfilters.PersianNormalizationFilterFactory;
import org.elasticsearch.analyzer.tokenfilters.PersianStemFilterFactory;
import org.elasticsearch.analyzer.tokenfilters.PersianStopFilterFactory;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.CharFilterFactory;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.indices.analysis.AnalysisModule.AnalysisProvider;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Nariman on 10/26/2017.
 */
public class ParsiAnalyzerPlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> analyzers = new HashMap<>();
        analyzers.put("parsi", ParsiAnalyzerProvider::new);
        analyzers.put("parsi_standard", ParsiStandardAnalyzerProvider::new);
        return analyzers;
    }

    @Override
    public Map<String, AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
        Map<String, AnalysisProvider<TokenFilterFactory>> tokenFilters = new HashMap();
        tokenFilters.put("parsi_normalizer", PersianNormalizationFilterFactory::new);
        tokenFilters.put("parsi_stem_filter", PersianStemFilterFactory::new);
        tokenFilters.put("parsi_stop_filter", PersianStopFilterFactory::new);
        return tokenFilters;
    }

    @Override
    public Map<String, AnalysisProvider<CharFilterFactory>> getCharFilters() {
        return Collections.singletonMap("zwnj_filter", ZeroWidthNonJoinerCharFilterFactory::new);
    }

}
