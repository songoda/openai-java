package com.theokanning.openai.classifications;

import lombok.Data;

import java.util.List;

@Data
public class ClassificationResult {

    String completion;

    String label;

    String model;

    String object;

    List<SelectedExample> selected_examples;

}
