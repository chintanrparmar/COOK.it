package it.cook.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import it.cook.network.ApiHelper
import it.cook.repository.MainRepository
import it.cook.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

