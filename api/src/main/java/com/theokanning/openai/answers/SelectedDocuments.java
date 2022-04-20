package com.theokanning.openai.answers;

import lombok.Data;

/**
 * An object containing a response from the answers api
 * <p>
 * https://beta.openai.com/docs/api-reference/answers
 */
@Data
public class SelectedDocuments {

    Integer document;

    String text;
}
