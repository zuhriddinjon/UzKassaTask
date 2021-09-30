package uz.instat.fitneskittest.busines.network.data

import com.google.gson.annotations.SerializedName

data class News (
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Long,
    @SerializedName("articles")
    val articles: List<ArticleDomain>
)