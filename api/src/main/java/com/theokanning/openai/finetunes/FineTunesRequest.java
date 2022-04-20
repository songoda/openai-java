package com.theokanning.openai.finetunes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A request to the fine-tunes api.
 * <p>
 * https://beta.openai.com/docs/api-reference/fine-tunes
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FineTunesRequest {

    /**
     * The ID of an uploaded file that contains training data.
     */
    String training_file;

    /**
     * The ID of an uploaded file that contains validation data.
     */
    String validation_file;

    /**
     * ID of the engine to use for completion
     */
    String model;
}
