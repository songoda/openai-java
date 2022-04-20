package com.theokanning.openai.files;

import lombok.Data;

/**
 * An object containing a response from the open ai api.
 * <p>
 * https://beta.openai.com/docs
 */
@Data
public class OpenAiFile {

    String id;

    String object;

    int bytes;

    long created_at;

    String filename;

    String purpose;

}
