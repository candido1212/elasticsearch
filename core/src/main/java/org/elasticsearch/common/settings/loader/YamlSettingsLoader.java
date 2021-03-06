/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.common.settings.loader;

import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Map;

/**
 * Settings loader that loads (parses) the settings in a yaml format by flattening them
 * into a map.
 */
public class YamlSettingsLoader extends XContentSettingsLoader {

    public YamlSettingsLoader(boolean allowNullValues) {
        super(allowNullValues);
    }

    @Override
    public XContentType contentType() {
        return XContentType.YAML;
    }

    @Override
    public Map<String, String> load(String source) throws IOException {
        /*
         * #8259: Better handling of tabs vs spaces in elasticsearch.yml
         */
        if (source.indexOf('\t') > -1) {
            throw new IOException("Tabs are illegal in YAML.  Did you mean to use whitespace character instead?");
        }
        return super.load(source);
    }
}
