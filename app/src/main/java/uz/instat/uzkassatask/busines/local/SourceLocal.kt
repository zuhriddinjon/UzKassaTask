package uz.instat.fitneskittest.busines.network.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceLocal(
    var id: String?,
    var name: String
) : Parcelable