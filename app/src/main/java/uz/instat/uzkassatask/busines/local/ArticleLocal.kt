package uz.instat.uzkassatask.busines.local

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize
import uz.instat.fitneskittest.busines.network.data.SourceLocal

@Parcelize
class ArticleLocal(
    var source: SourceLocal,
    var author: String?,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String?,
    var publishedAt: String,
    var content: String
) : Parcelable {
    companion object {
        class ArticleCallBack : DiffUtil.ItemCallback<ArticleLocal>() {
            override fun areItemsTheSame(
                oldItem: ArticleLocal,
                newItem: ArticleLocal
            ): Boolean {
                return oldItem.source == newItem.source
            }

            override fun areContentsTheSame(
                oldItem: ArticleLocal,
                newItem: ArticleLocal
            ): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}