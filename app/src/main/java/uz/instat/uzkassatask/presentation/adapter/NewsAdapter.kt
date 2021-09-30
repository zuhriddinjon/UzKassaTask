package uz.instat.uzkassatask.presentation.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.instat.uzkassatask.busines.local.ArticleLocal
import uz.instat.uzkassatask.databinding.ItemNewsBinding
import javax.inject.Inject

class LessonAdapter @Inject constructor() :
    ListAdapter<ArticleLocal, RecyclerView.ViewHolder>(ArticleLocal.Companion.ArticleCallBack()) {

    class LessonVH(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: ArticleLocal) {
            binding.tvName.text = item.source.name
            binding.tvTitle.text = item.title
            binding.ivImage
        }
    }
}

interface IOnItemClickListener {
    fun onItemClick()
}