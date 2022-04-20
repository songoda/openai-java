package com.theokanning.openai.answers;

import com.theokanning.openai.CommonRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * A request to the answers api.
 *
 * https://beta.openai.com/docs/api-reference/answers
 */
@NoArgsConstructor
@Data
public class AnswersRequest extends CommonRequest {

    @Builder
    public AnswersRequest(List<String> stop, Integer maxTokens, Integer n, List<String> documents,
                          String question, String search_model, String model, String examples_context,
                          List<List<String>> examples, List<String> expand) {
        super(stop, maxTokens, n);
        this.documents = documents;
        this.question = question;
        this.search_model = search_model;
        this.model = model;
        this.examples_context = examples_context;
        this.examples = examples;
        this.expand = expand;
    }

    /**
     * Documents to search over
     */
    List<String> documents;

    /**
     * Question to be answered
     */
    String question;

    /**
     * ID of the engine to use for search
     */
    String search_model;

    /**
     * ID of the engine to use for completion
     */
    String model;

    /**
     * A text snippet containing the contextual information used to generate
     * the answers for the examples you provide
     */
    String examples_context;

    /**
     * List of (question, answer) pairs that will help steer the model towards
     * the tone and answer format you'd like. We recommend adding 2 to 3 examples.
     */
    List<List<String>> examples;

    /**
     * If an object name is in the list, we provide the full information of the object;
     * otherwise, we only provide the object ID. Currently we support completion
     * and file objects for expansion.
     */
    List<String> expand;
}
