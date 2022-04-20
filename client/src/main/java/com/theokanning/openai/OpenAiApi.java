package com.theokanning.openai;

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
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;

public interface OpenAiApi {

    @GET("/v1/engines")
    Single<OpenAiResponse<Engine>> getEngines();

    @GET("/v1/engines/{engine_id}")
    Single<Engine> getEngine(@Path("engine_id") String engineId);

    @POST("/v1/engines/{engine_id}/completions")
    Single<CompletionResult> createCompletion(@Path("engine_id") String engineId, @Body CompletionRequest request);

    @POST("/v1/engines/{engine_id}/search")
    Single<OpenAiResponse<SearchResult>> search(@Path("engine_id") String engineId, @Body SearchRequest request);

    @POST("/v1/answers")
    Single<AnswersResult> askQuestion(@Body AnswersRequest request);

    @POST("/v1/fine-tunes")
    Single<FineTunesResult> createFineTune(@Body FineTunesRequest request);

    @GET("/v1/fine-tunes")
    Single<OpenAiResponse<FineTunesResult>> getFineTunes();

    @POST("/v1fine-tunes/{fine_tune_id}/cancel")
    Single<FineTunesResult> cancelFineTune(@Path("fine_tune_id") String fineTuneId);

    @GET("/v1/files")
    Single<OpenAiResponse<OpenAiFile>> getFiles();

    @Multipart
    @POST("/v1/files")
    Single<OpenAiFile> uploadFile(@Part MultipartBody.Part file, @Part("purpose") RequestBody purpose);

    @DELETE("/v1/files/{file_id}")
    Single<DeleteFileResult> deleteFile(@Path("file_id") String fileId);

    @POST("/v1/classifications")
    Single<ClassificationResult> createClassification(@Body ClassificationRequest request);

}
