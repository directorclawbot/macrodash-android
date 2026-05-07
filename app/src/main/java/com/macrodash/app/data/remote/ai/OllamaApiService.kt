package com.macrodash.app.data.remote.ai

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OllamaApiService {
    @GET("/api/tags")
    suspend fun listModels(): OllamaModelsResponse

    @POST("/api/chat")
    suspend fun chat(@Body request: OllamaChatRequest): OllamaChatResponse
}

data class OllamaModelsResponse(
    @SerializedName("models") val models: List<OllamaModel>
)

data class OllamaModel(
    @SerializedName("name") val name: String,
    @SerializedName("size") val size: Long? = null,
    @SerializedName("modified_at") val modifiedAt: String? = null
)

data class OllamaChatRequest(
    @SerializedName("model") val model: String,
    @SerializedName("messages") val messages: List<OllamaMessage>,
    @SerializedName("stream") val stream: Boolean = false
)

data class OllamaMessage(
    @SerializedName("role") val role: String,
    @SerializedName("content") val content: String
)

data class OllamaChatResponse(
    @SerializedName("message") val message: OllamaResponseMessage,
    @SerializedName("done") val done: Boolean = true
)

data class OllamaResponseMessage(
    @SerializedName("content") val content: String
)