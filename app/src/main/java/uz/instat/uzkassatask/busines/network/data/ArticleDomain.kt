package uz.instat.fitneskittest.busines.network.data

import com.google.gson.annotations.SerializedName


data class ArticleDomain(
    @SerializedName("source")
    var source: SourceDomain,
    @SerializedName("author")
    var author: String?,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("urlToImage")
    var urlToImage: String?,
    @SerializedName("publishedAt")
    var publishedAt: String,
    @SerializedName("content")
    var content: String
)