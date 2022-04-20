package com.theokanning.openai.files;

import lombok.Data;

@Data
public class DeleteFileResult {

    String id;

    String object;

    boolean deleted;

}
