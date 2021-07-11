package com.example.templateapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SimpleRepository,
    @ConfigurationString val config: String
) : ViewModel() {

    val data = MutableLiveData<String>()

    fun thing() {
        repository.store("$config ${repository.hashCode().toString().takeLast(6)} ")
    }

    init {
        listen()
    }

    private fun listen() {
        repository.onChanged {
            data.value = it
        }
    }

}

interface SimpleRepository {
    fun store(string: String)
    fun retrieve(): String
    fun onChanged(function: (String) -> Unit)
}

@ActivityRetainedScoped
class RepositoryImpl @Inject constructor() : SimpleRepository {

    private var listener: ((String) -> Unit)? = null
    private var value: String = ""

    override fun store(string: String) {
        value = string
        listener?.invoke(string)
    }

    override fun retrieve(): String {
        return value
    }

    override fun onChanged(function: (String) -> Unit) {
        listener = function
    }

}

