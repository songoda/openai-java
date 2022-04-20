package com.theokanning.openai.classifications;

import lombok.Data;

@Data
public class SelectedExample {

    Integer document;

    String lavel;

    String text;
}
