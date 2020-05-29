package it.cook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavDrawerState : ViewModel() {
    private val _isOpen = MutableLiveData<Boolean>()
    val isOpen: LiveData<Boolean>
        get() = _isOpen

    fun updateNavDrawer(state: Boolean) = _isOpen.postValue(state)
}