package com.abnamro.nl.toggle.client;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class YAMLClientIsEnabledTest {

    private final String FILE_NAME = "src/test/resources/features.yml";
    private final String FEATURE_ON_FILE_ENABLED = "featureOnFileEnabled";
    private final String FEATURE_ON_FILE_DISABLED = "featureOnFileDisabled";
    private final String FEATURE_NOT_ON_FILE_ENABLED = "featureNotOnFileEnabled";
    private final String FEATURE_NOT_ON_FILE_DISABLED = "featureNotOnFileDisabled";
    private final String FEATURE_NOT_ON_FILE_AND_NOT_ON_DEFAULTS = "featureNotOnFileAndNotOnDefaults";

    private YAMLClient client;

    private Map<String, Boolean> defaultValues = createDefaultValues();

    @Test
    public void file_not_exist() {
        client = new YAMLClient("invalid.yml", defaultValues);
        assertTrue(client.isEnabled(FEATURE_ON_FILE_ENABLED));
        assertTrue(client.isEnabled(FEATURE_ON_FILE_DISABLED));
    }

    @Test
    public void feature_not_exist_on_file() {
        client = new YAMLClient(FILE_NAME, defaultValues);
        assertTrue(client.isEnabled(FEATURE_NOT_ON_FILE_ENABLED));
        assertFalse(client.isEnabled(FEATURE_NOT_ON_FILE_DISABLED));
    }

    @Test
    public void feature_exist_on_file() {
        client = new YAMLClient(FILE_NAME, defaultValues);
        assertTrue(client.isEnabled(FEATURE_ON_FILE_ENABLED));
        assertFalse(client.isEnabled(FEATURE_ON_FILE_DISABLED));
    }

    @Test
    public void feature_not_exist_on_file_nor_on_default_values() {
        client = new YAMLClient(FILE_NAME, defaultValues);
        assertFalse(client.isEnabled(FEATURE_NOT_ON_FILE_AND_NOT_ON_DEFAULTS));
    }

    private Map<String, Boolean> createDefaultValues() {
        Map<String, Boolean> values = new LinkedHashMap<>();
        values.put(FEATURE_ON_FILE_ENABLED, true);
        values.put(FEATURE_ON_FILE_DISABLED, true);
        values.put(FEATURE_NOT_ON_FILE_ENABLED, true);
        values.put(FEATURE_NOT_ON_FILE_DISABLED, false);
        return values;
    }


}
