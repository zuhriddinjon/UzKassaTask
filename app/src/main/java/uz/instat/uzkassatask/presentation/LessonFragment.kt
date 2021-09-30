package uz.instat.fitneskittest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import uz.instat.uzkassatask.busines.enums.Errors.Companion.message
import uz.instat.uzkassatask.busines.interactors.UiState
import uz.instat.uzkassatask.busines.local.ArticleLocal
import uz.instat.fitneskittest.databinding.FragmentLessonBinding
import uz.instat.uzkassatask.framework.MainViewModel
import uz.instat.uzkassatask.presentation.adapter.LessonAdapter
import javax.inject.Inject

@AndroidEntryPoint
class LessonFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels({ requireParentFragment() })

    @Inject
    lateinit var adapter: LessonAdapter

    private var _binding: FragmentLessonBinding? = null
    private val binding get() = _binding!!

    private val lessonObserver = Observer<UiState<List<ArticleLocal>>> {
        when (it) {
            is UiState.Loading -> {
                binding.fmProgress.isVisible = true
            }
            is UiState.Success -> {
                binding.fmProgress.isVisible = false
                if (it.data != null && it.data.isNotEmpty()) {
                    val list = arrayListOf<String>()
                    var time: String
                    it.data.forEach { item ->
                        if (!list.contains(item.date)) {
                            time = item.date
                            list.add(time)
                        }
                    }

                    val lessons = arrayListOf<ArticleLocal>()
                    list.forEach { date ->
                        lessons.add(ArticleLocal(date = date))
                        it.data.forEach { data ->
                            if (date == data.date)
                                lessons.add(data)
                        }
                    }

                    adapter.submitList(lessons)
                }
            }
            is UiState.Error -> {
                binding.fmProgress.isVisible = false
                Snackbar.make(requireView(), it.error.message ?: "Error!", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLessonBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.recycler.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.lessonState.observe(viewLifecycleOwner, lessonObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}