package com.theokanning.openai.classifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassificationRequest {

        List<List<String>> examples;

        String query;

        String search_model;

        String model;

        List<String> labels;

}
