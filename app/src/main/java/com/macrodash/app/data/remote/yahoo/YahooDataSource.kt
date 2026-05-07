package com.macrodash.app.data.remote.yahoo

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

interface YahooDataSource {
    @GET("v7/finance/quote")
    suspend fun getQuotes(@Query("symbols") symbols: String): YahooQuoteResponse
}

data class YahooQuoteResponse(
    @SerializedName("quoteResponse") val quoteResponse: YahooQuoteResult?
)

data class YahooQuoteResult(
    @SerializedName("result") val result: List<YahooQuote>,
    @SerializedName("error") val error: String? = null
)

data class YahooQuote(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("shortName") val shortName: String? = null,
    @SerializedName("longName") val longName: String? = null,
    @SerializedName("regularMarketPrice") val regularMarketPrice: Double? = null,
    @SerializedName("regularMarketChangePercent") val regularMarketChangePercent: Double? = null,
    @SerializedName("regularMarketTime") val regularMarketTime: Long? = null
)