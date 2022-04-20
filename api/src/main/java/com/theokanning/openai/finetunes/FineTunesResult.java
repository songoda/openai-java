package com.theokanning.openai.finetunes;

import com.theokanning.openai.files.OpenAiFile;
import lombok.Data;

import java.util.List;

/**
 * An object containing a response from the answers api
 *
 * https://beta.openai.com/docs/api-reference/answers
 */
@Data
public class FineTunesResult {

    String id;

    String object;

    String model;

    long created_at;

    List<FineTuneEvent> events;

    String fine_tuned_model;

    HyperParams hyperParams;

    String organization_id;

    List<ResultFile> result_files;

    String status;

    List<OpenAiFile> validation_files;

    List<OpenAiFile> training_files;

    long updated_at;

}
