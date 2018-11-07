package com.abnamro.nl.toggle.client;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YAMLClient {

    private final Map<String, Boolean> defaultValues;
    private final String DEFAULT_FILE_NAME = "features.yml";

    private String fileName = DEFAULT_FILE_NAME;

    public YAMLClient(String featuresFileName, Map<String, Boolean> defaultValues) {
        this.defaultValues = defaultValues;

        if (featuresFileName != null && !featuresFileName.isEmpty()) {
            this.fileName = featuresFileName;
        }
    }

    public YAMLClient(Map<String, Boolean> defaultValues) {
        this.defaultValues = defaultValues;
    }

    public boolean isEnabled(String name) {
        Map<String, Boolean> features = getFeaturesFromFile();

        Boolean featureStatus = features.get(name);
        if (isPresentOnFile(featureStatus)) return featureStatus;

        featureStatus = getFromDefaultValues(name);
        return featureStatus != null ? featureStatus : false;
    }

    private Boolean getFromDefaultValues(String name) {
        return defaultValues.get(name);
    }

    private boolean isPresentOnFile(Boolean value) {
        return value != null;
    }

    String getFileName() {
        return fileName;
    }

    private Map<String, Boolean> getFeaturesFromFile() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
        return fileExists(is) ?
                new Yaml().<Map<String, Boolean>>load(is) : defaultValues;
    }

    private boolean fileExists(InputStream is) {
        return is != null;
    }


}
