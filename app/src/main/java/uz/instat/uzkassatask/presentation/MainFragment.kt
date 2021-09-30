package uz.instat.fitneskittest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.instat.fitneskittest.R
import uz.instat.fitneskittest.databinding.FragmentMainBinding
import uz.instat.uzkassatask.framework.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels({ requireParentFragment() })

    private var count = 0
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getLessons()
        viewModel.getVisits()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupViews()
    }

    private fun setupViewPager() {
        val fragmentList = arrayListOf(
            LessonFragment(),
            VisitsFragment()
        )
        count = fragmentList.size
        val vpAdapter = ViewPagerAdapter(
            fragmentList,
            parentFragmentManager,
            lifecycle
        )
        val marginPageTransformer = MarginPageTransformer(50)
//        viewModel.setViewPager(binding.viewPager)
//        viewModel.setCount(count)
        binding.viewPager.setPageTransformer(CompositePageTransformer().also {
            it.addTransformer(marginPageTransformer)
        })
        binding.viewPager.adapter = vpAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            if (position == 0)
                tab.setText(R.string.trenirovki)
            else
                tab.setText(R.string.posesheniya)
        }.attach()
    }

    private fun setupViews() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}