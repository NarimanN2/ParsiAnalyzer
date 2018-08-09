package org.elasticsearch.analyzer.characterfilters;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractCharFilterFactory;

import java.io.Reader;

public class ZeroWidthNonJoinerCharFilterFactory extends AbstractCharFilterFactory {


    public ZeroWidthNonJoinerCharFilterFactory(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        super(indexSettings, name);
    }

    @Override
    public Reader create(Reader reader) {
        return new ZeroWidthNonJoinerCharFilter(reader);
    }
}
