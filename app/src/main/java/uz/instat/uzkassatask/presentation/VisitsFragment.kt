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
import uz.instat.fitneskittest.databinding.FragmentVisitBinding
import uz.instat.uzkassatask.framework.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class VisitsFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels({ requireParentFragment() })

    private var _binding: FragmentVisitBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapter: VisitAdapter

    private val visitObserver = Observer<UiState<List<VisitsHistoryLocal>>> {
        when (it) {
            is UiState.Loading -> {
                binding.fmProgress.isVisible = true
            }
            is UiState.Success -> {
                binding.fmProgress.isVisible = false
                adapter.submitList(it.data)
            }
            is UiState.Error -> {
                binding.fmProgress.isVisible = false
                Snackbar.make(requireView(),it.error.message?:"Error!",Snackbar.LENGTH_SHORT).show()
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVisitBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.recycler.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.visitState.observe(viewLifecycleOwner, visitObserver)
    }
}