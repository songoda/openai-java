package com.theokanning.openai.completion;

import com.theokanning.openai.CommonRequest;
import lombok.*;

import java.util.List;

/**
 * A request for OpenAi to generate a predicted completion for a prompt.
 * All fields are nullable.
 * <p>
 * Documentation taken from
 * https://beta.openai.com/docs/api-reference/create-completion
 */
@Getter
@Data
public class CompletionRequest extends CommonRequest {

    @Builder
    public CompletionRequest(List<String> stop, Integer maxTokens, String prompt, Double temperature,
                             Double topP, Integer n, Boolean stream, Integer logprobs, Boolean echo,
                             Double presencePenalty, Double frequencyPenalty, Integer bestOf, String model) {
        super(stop, maxTokens, n);
        this.prompt = prompt;
        this.temperature = temperature;
        this.topP = topP;
        this.stream = stream;
        this.logprobs = logprobs;
        this.echo = echo;
        this.presencePenalty = presencePenalty;
        this.frequencyPenalty = frequencyPenalty;
        this.bestOf = bestOf;
        this.model = model;
    }

    /**
     * An optional prompt to complete from
     */
    String prompt;

    /**
     * What sampling temperature to use. Higher values means the model will take more risks.
     * Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer.
     * <p>
     * We generally recommend using this or {@link top_p} but not both.
     */
    Double temperature;

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of
     * the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are
     * considered.
     * <p>
     * We generally recommend using this or {@link temperature} but not both.
     */
    Double topP;

    /**
     * Whether to stream back partial progress.
     * If set, tokens will be sent as data-only server-sent events as they become available,
     * with the stream terminated by a data: DONE message.
     */
    Boolean stream;

    /**
     * Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens.
     * For example, if logprobs is 10, the API will return a list of the 10 most likely tokens.
     * The API will always return the logprob of the sampled token,
     * so there may be up to logprobs+1 elements in the response.
     */
    Integer logprobs;

    /**
     * Echo back the prompt in addition to the completion
     */
    Boolean echo;

    /**
     * Number between 0 and 1 (default 0) that penalizes new tokens based on whether they appear in the text so far.
     * Increases the model's likelihood to talk about new topics.
     */
    Double presencePenalty;

    /**
     * Number between 0 and 1 (default 0) that penalizes new tokens based on their existing frequency in the text so far.
     * Decreases the model's likelihood to repeat the same line verbatim.
     */
    Double frequencyPenalty;

    /**
     * Generates best_of completions server-side and returns the "best"
     * (the one with the lowest log probability per token).
     * Results cannot be streamed.
     * <p>
     * When used with {@link n}, best_of controls the number of candidate completions and n specifies how many to return,
     * best_of must be greater than n.
     */
    Integer bestOf;

    /**
     * ID of the engine to use for completion
     */
    String model;
}
