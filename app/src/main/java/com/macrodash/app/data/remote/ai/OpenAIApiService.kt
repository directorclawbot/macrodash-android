package com.macrodash.app.data.remote.ai

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAIApiService {
    @POST("chat/completions")
    suspend fun chatCompletions(@Body request: OpenAIChatRequest): OpenAIChatResponse
}

data class OpenAIChatRequest(
    @SerializedName("model") val model: String,
    @SerializedName("messages") val messages: List<OpenAIMessage>,
    @SerializedName("max_tokens") val maxTokens: Int = 800
)

data class OpenAIMessage(
    @SerializedName("role") val role: String,
    @SerializedName("content") val content: String
)

data class OpenAIChatResponse(
    @SerializedName("choices") val choices: List<OpenAIChoice>,
    @SerializedName("usage") val usage: OpenAIUsage? = null
)

data class OpenAIChoice(
    @SerializedName("message") val message: OpenAIResponseMessage
)

data class OpenAIResponseMessage(
    @SerializedName("content") val content: String
)

data class OpenAIUsage(
    @SerializedName("prompt_tokens") val promptTokens: Int,
    @SerializedName("completion_tokens") val completionTokens: Int,
    @SerializedName("total_tokens") val totalTokens: Int
)