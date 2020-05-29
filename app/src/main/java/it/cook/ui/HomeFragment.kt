package it.cook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import it.cook.databinding.FragmentHomeBinding
import it.cook.model.Recipe
import it.cook.model.RecipeData
import it.cook.model.State
import it.cook.network.ApiHelper
import it.cook.network.RetrofitBuilder
import it.cook.ui.adapter.RecipeListAdapter
import it.cook.utils.ViewModelFactory
import it.cook.viewmodel.MainViewModel
import it.cook.viewmodel.NavDrawerState

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: MainViewModel
    private lateinit var navDrawerState: NavDrawerState

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupObservers()
        setupOnClick()
    }

    private fun setupOnClick() {
        activity?.run {
            navDrawerState = ViewModelProvider(this).get(NavDrawerState::class.java)
        } ?: throw Throwable("invalid activity")
        binding.menuIv.setOnClickListener { navDrawerState.updateNavDrawer(true) }
    }

    private fun setupViewModel() {

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
        viewModel.getRecipes()
    }

    private fun setupObservers() {
        viewModel.recipeLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { state ->
                when (state) {
                    is State.Loading -> {

                    }
                    is State.Success -> {
                        state.data.recipes?.let { it1 -> setAdapter(it1) }
                    }
                    is State.Error -> {

                    }
                }
            }
        })
    }

    private fun setAdapter(recipes: List<Recipe>) {
        binding.breakFastRv.adapter = RecipeListAdapter(recipes)
        binding.lunchRv.adapter = RecipeListAdapter(recipes)
        binding.dinnerRv.adapter = RecipeListAdapter(recipes)
    }

}