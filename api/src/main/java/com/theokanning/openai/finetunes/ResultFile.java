package com.theokanning.openai.finetunes;

import lombok.Data;

/**
 * An object containing a response from the fine tunes api
 * <p>
 * https://beta.openai.com/docs/api-reference/fine-tunes
 */
@Data
public class ResultFile {

    String id;

    String object;

    int bytes;

    long created_at;

    String filename;

    String purpose;

}
