package uz.instat.fitneskittest.busines.network.data

import com.google.gson.annotations.SerializedName


data class SourceDomain(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String

)