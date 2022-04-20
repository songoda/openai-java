package com.theokanning.openai;

public enum Purpose {

    SEARCH, ANSWERS, CLASSIFICATIONS, FINE_TUNE;

    public String getName() {
        return this.name().toLowerCase().replace("_", "-");
    }
}
