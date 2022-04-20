package com.theokanning.openai.answers;

import lombok.Data;

import java.util.List;

/**
 * An object containing a response from the answers api
 *
 * https://beta.openai.com/docs/api-reference/answers
 */
@Data
public class AnswersResult {

    List<String> answers;

    String completion;

    String model;

    String object;

    String search_model;

    List<SelectedDocuments> selected_documents;
}
