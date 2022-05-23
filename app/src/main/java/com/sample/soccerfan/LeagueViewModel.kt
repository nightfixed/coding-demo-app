package com.sample.soccerfan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.soccerfan.model.Squad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SquadViewModel: ViewModel() {

    private val repository = Repository()

    val liveDataSquad: MutableLiveData<Result<Squad>> = MutableLiveData()

    fun getSquad() {
        liveDataSquad.value = Result.Loading(true)
        viewModelScope.launch {
            repository.getSquad().collect { squad ->
                withContext(Dispatchers.Main) {
                    liveDataSquad.value = Result.Loading(false)
                    liveDataSquad.value = Result.Success(squad)
                }
            }
        }
    }
}