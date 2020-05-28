package it.cook.viewmodel

import androidx.lifecycle.*
import it.cook.model.RecipeData
import it.cook.model.State
import it.cook.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _mainLiveData = MutableLiveData<State<RecipeData>>()
    val recipeLiveData: LiveData<State<RecipeData>> get() = _mainLiveData



    fun getRecipes() {
        _mainLiveData.value = State.loading()
        viewModelScope.launch(Dispatchers.IO) {
            val mainResponse = mainRepository.getRecipes()
            withContext(Main) {

                mainResponse.let { mainData ->
                    mainData.recipes?.let {
                        if (it.isNotEmpty()) {
                            _mainLiveData.value = State.success(mainResponse)
                        } else {
                            _mainLiveData.value = State.error("No Data Available")
                        }
                    }

                } ?: run {
                    _mainLiveData.value = State.error("Something went wrong")
                }


            }
        }
    }

}