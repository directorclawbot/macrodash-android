package com.macrodash.app.data.remote.fred

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

interface FredApiService {
    @GET("fred/series/observations")
    suspend fun getSeriesObservations(
        @Query("series_id") seriesId: String,
        @Query("api_key") apiKey: String,
        @Query("file_type") fileType: String = "json",
        @Query("observation_start") observationStart: String = "latest"
    ): FredObservationsResponse
}

data class FredObservationsResponse(
    @SerializedName("seriess") val seriess: List<FredSeries>? = null,
    @SerializedName("observations") val observations: List<FredObservation>? = null
)

data class FredSeries(
    @SerializedName("series_id") val seriesId: String,
    @SerializedName("title") val title: String,
    @SerializedName("units") val units: String,
    @SerializedName("last_updated") val lastUpdated: String
)

data class FredObservation(
    @SerializedName("date") val date: String,
    @SerializedName("value") val value: String
)