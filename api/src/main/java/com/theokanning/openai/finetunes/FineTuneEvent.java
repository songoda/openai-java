package com.theokanning.openai.finetunes;

import lombok.Data;

/**
 * An object containing a response from the fine tunes api
 * <p>
 * https://beta.openai.com/docs/api-reference/fine-tunes
 */
@Data
public class FineTuneEvent {

    String object;

    long created_at;

    String level;

    String message;

}
