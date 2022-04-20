package example;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.answers.AnswersRequest;
import com.theokanning.openai.classifications.ClassificationRequest;
import com.theokanning.openai.completion.CompletionRequest;

import java.io.File;
import java.util.Arrays;
import java.util.List;

class OpenAiApiExample {

    public static void main(String... args) {
        String token = "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";
        OpenAiService service = new OpenAiService(token);

/*
        System.out.println("\nGetting available engines...");
        service.getEngines().forEach(System.out::println);

        System.out.println("\nGetting ada engine...");
        Engine ada = service.getEngine("ada");
        System.out.println(ada);

        System.out.println("\nCreating completion...");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Somebody once told me the world is gonna roll me")
                .echo(true)
                .model("ada")
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);

        System.out.println("\nSearching documents...");
        SearchRequest searchRequest = SearchRequest.builder()
                .documents(Arrays.asList("Water", "Earth", "Electricity", "Fire"))
                .query("Pikachu")
                .build();
        service.search("ada", searchRequest).forEach(System.out::println);
*/
        System.out.println("\nGetting an answer...");
        AnswersRequest answersRequest = AnswersRequest.builder()
                .documents(Arrays.asList("Puppy A is happy.", "Puppy B is sad."))
                .question("which puppy is happy?")
                .search_model("ada")
                .model("ada")
                .examples_context("In 2017, U.S. life expectancy was 78.6 years.")
                .examples(List.of(List.of("What is human life expectancy in the United States?", "78 years.")))
                .maxTokens(5)
                .expand(List.of("Puppy"))
                .build();
        service.askQuestion(answersRequest).getAnswers().forEach(System.out::println);
/*
        System.out.println("\nCreating a fine tune...");
        FineTunesRequest fineTunesRequest = FineTunesRequest.builder()
                .training_file("file-XGinujblHPwGLSztz8cPS8XY")
                .model("ada")
                .build();
        System.out.println(service.createFineTune(fineTunesRequest).getStatus());

        System.out.println("\nListing fine tunes...");
        service.getFineTunes().stream().map(f -> f.getId() + " : " + f.getStatus()).forEach(System.out::println);


        System.out.println("\nListing files...\n " + service.getFiles());

        System.out.println("\nUploading a File...");
        File file = new File("puppy.jsonl");
        if (!file.exists()) {
            System.out.println("File not found. Skipping test.");
        } else {
            System.out.println(service.uploadFile(file, "answers").getFilename());
        }

        System.out.println("\nDeleting a File...");
        System.out.println(service.deleteFile("file-cquHoBA4fCUkUgYjSton7LZb").isDeleted());

        System.out.println("\nCreating a classification...");
        ClassificationRequest classificationRequest = ClassificationRequest.builder()
                .examples(List.of(List.of("A happy moment", "Positive"),
                        List.of("I am sad.", "Negative"),
                        List.of("I am feeling awesome", "Positive")))
                .labels(List.of("Positive", "Negative", "Neutral"))
                .query("It is a raining day :(")
                .search_model("ada")
                .model("curie")
                .build();
        System.out.println(service.createClassification(classificationRequest).getLabel());
*/
    }
}