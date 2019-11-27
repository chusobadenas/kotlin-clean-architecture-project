package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory
@Inject
constructor(private val viewModelMap: Map<Class<out ViewModel>, ViewModel>) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelMap[modelClass] as T
    }
}
