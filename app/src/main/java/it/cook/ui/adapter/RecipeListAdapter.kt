package it.cook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import it.cook.databinding.RecipeItemBinding
import it.cook.model.Recipe

class RecipeListAdapter(private val list: List<Recipe>) :
    RecyclerView.Adapter<RecipeListAdapter.RecipeListView>() {

    class RecipeListView(private val binding: RecipeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipeModel: Recipe) {
            binding.foodTitleTv.text = recipeModel.title
            binding.foodTimeTv.text = "Cooking Time: ${recipeModel.cookingTime} Mins"
            binding.foodCoverIv.load(recipeModel.cover)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListView {
        return RecipeListView(
            RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecipeListView, position: Int) =
        holder.bind(list[position])
}