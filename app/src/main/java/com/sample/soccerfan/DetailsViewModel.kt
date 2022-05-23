package com.sample.soccerfan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.soccerfan.model.League
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel: ViewModel() {

    private val repository = Repository()

    val liveDataDisplay: MutableLiveData<Result<List<String>>> = MutableLiveData()
    val liveDataLeagueInfo: MutableLiveData<Result<League>> = MutableLiveData()

    fun getLeague() {
        liveDataLeagueInfo.value = Result.Loading(true)
        viewModelScope.launch {
            repository.getLeague().collect { league ->
                withContext(Dispatchers.Main) {
                    liveDataLeagueInfo.value = Result.Loading(false)
                    liveDataLeagueInfo.value = Result.Success(league)
                }
            }
        }
    }

    fun getDisplaySections() {
        liveDataDisplay.value = Result.Loading(true)
        viewModelScope.launch {
            repository.getDisplayCategories().collect { displayList ->
                withContext(Dispatchers.Main) {
                    liveDataDisplay.value = Result.Loading(false)
                    liveDataDisplay.value = Result.Success(displayList)
                }
            }
        }
    }
}