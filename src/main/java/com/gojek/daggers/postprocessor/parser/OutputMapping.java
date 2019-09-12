package com.gojek.daggers.postprocessor.parser;

import java.io.Serializable;
import java.util.HashMap;

public class OutputMapping implements Serializable, Validator {

    private String path;

    public OutputMapping(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void validate() throws IllegalArgumentException {
        HashMap<String, Object> mandatoryFields = new HashMap<>();
        mandatoryFields.put("path", path);

        validateFields(mandatoryFields);
    }
}
