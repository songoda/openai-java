package com.theokanning.openai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.theokanning.openai.answers.AnswersRequest;
import com.theokanning.openai.answers.AnswersResult;
import com.theokanning.openai.classifications.ClassificationRequest;
import com.theokanning.openai.classifications.ClassificationResult;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.engine.Engine;
import com.theokanning.openai.files.DeleteFileResult;
import com.theokanning.openai.files.OpenAiFile;
import com.theokanning.openai.finetunes.FineTunesRequest;
import com.theokanning.openai.finetunes.FineTunesResult;
import com.theokanning.openai.search.SearchRequest;
import com.theokanning.openai.search.SearchResult;
import okhttp3.*;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OpenAiService {

    private final OpenAiApi api;

    public OpenAiService(String token) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new AuthenticationInterceptor(token))
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
                .addInterceptor(new LoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openai.com/")
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(OpenAiApi.class);
    }

    public List<Engine> getEngines() {
        return api.getEngines().blockingGet().data;
    }

    public Engine getEngine(String engineId) {
        return api.getEngine(engineId).blockingGet();
    }

    public CompletionResult createCompletion(String engine, CompletionRequest request) {
        return api.createCompletion(engine, request).blockingGet();
    }

    public List<SearchResult> search(String engineId, SearchRequest request) {
        return api.search(engineId, request).blockingGet().data;
    }

    public AnswersResult askQuestion(AnswersRequest request) {
        return api.askQuestion(request).blockingGet();
    }

    public FineTunesResult createFineTune(FineTunesRequest request) {
        return api.createFineTune(request).blockingGet();
    }

    public List<FineTunesResult> getFineTunes() {
        return api.getFineTunes().blockingGet().data;
    }

    public FineTunesResult cancelFineTune(String fineTuneId) {
        return api.cancelFineTune(fineTuneId).blockingGet();
    }

    public List<OpenAiFile> getFiles() {
        return api.getFiles().blockingGet().data;
    }

    public DeleteFileResult deleteFile(String id) {
        return api.deleteFile(id).blockingGet();
    }

    public OpenAiFile uploadFile(File file, Purpose purpose) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/json"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        RequestBody requestPurpose = RequestBody.create(MediaType.parse("text/plain"), purpose.getName());
        return api.uploadFile(filePart, requestPurpose).blockingGet();
    }

    public ClassificationResult createClassification(ClassificationRequest request) {
        return api.createClassification(request).blockingGet();
    }

    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            if (chain.request().method().equals("POST")) {
                System.out.println(String.format("Sending request %s on %s%n%s",
                        request.url(), chain.connection(), request.headers()));

                final Request copy = request.newBuilder().build();
                final Buffer buffer = new Buffer();
                copy.body().writeTo(buffer);
                System.out.println(buffer.readUtf8());
            }

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            if (chain.request().method().equals("POST")) {
                System.out.println(String.format("Received response for %s in %.1fms%n%s",
                        response.request().url(), (t2 - t1) / 1e6d, response.headers()));

                System.out.println(response.peekBody(Long.MAX_VALUE).string());
            }

            return response;
        }
    }
}
