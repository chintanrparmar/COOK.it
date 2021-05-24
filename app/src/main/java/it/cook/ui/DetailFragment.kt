package it.cook.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import it.cook.R
import it.cook.databinding.FragmentDetailBinding
import it.cook.model.Recipe
import it.cook.model.State
import it.cook.network.ApiHelper
import it.cook.network.RetrofitBuilder
import it.cook.utils.ViewModelFactory
import it.cook.viewmodel.MainViewModel


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private lateinit var viewModel: MainViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
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
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        binding.shareRecipeBt.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_SEND
            i.putExtra(
                Intent.EXTRA_TEXT,
                "For ${binding.titleTv.text} and more amazing recipes, Download COOK.it now!"
            )
            i.type = "text/plain"
            startActivity(
                Intent.createChooser(
                    i,
                    "Share ${getString(R.string.app_name)}"
                )
            )
        }
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
                        binding.loader.loadingView.visibility = View.VISIBLE
                    }
                    is State.Success -> {
                        binding.loader.loadingView.visibility = View.GONE
                        state.data.recipes?.let { list ->
                            val data =
                                list.filter { recipe -> recipe.id == arguments?.getInt("recipeId") }[0]
                            setData(data)
                        }
                    }
                    is State.Error -> {
                        binding.loader.loadingView.visibility = View.GONE
                        Toast.makeText(activity, state.message, Toast.LENGTH_SHORT).show()

                    }
                }
            }
        })
    }

    fun setData(recipe: Recipe) {
        binding.titleTv.text = recipe.title
        binding.cookTimeTv.text = getString(R.string.cooking_time, recipe.cookingTime)
        binding.prepTimeTv.text = getString(R.string.preparation_time, recipe.prepTime)
        binding.ingredientsTv.text = recipe.ingredients?.replace("\\n", "\n\n")
        binding.stepsTv.text = recipe.stepsGuide?.replace("\\n", "\n\n")
        binding.foodCoverIv.load(recipe.cover)

    }
}