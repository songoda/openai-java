package com.theokanning.openai.finetunes;

import lombok.Data;

/**
 * An object containing a response from the fine tunes api
 * <p>
 * https://beta.openai.com/docs/api-reference/fine-tunes
 */
@Data
public class HyperParams {

    int batch_size;

    double learning_rate_multiplier;

    int n_epochs;

    double prompt_loss_weight;

}
