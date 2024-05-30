package com.ms.ekacaretest

import com.ms.ekacaretest.database.PersonDetails
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonViewModel(private val context: Application): AndroidViewModel(context)  {

    var liveDataPersonDetailsList = MutableLiveData<List<PersonDetails>>()


    fun getPersonList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response  = DetailsRepository.fetchData(context)
            withContext(Dispatchers.Main) {
                liveDataPersonDetailsList.postValue(response)
            }
        }
    }

    fun insertData(personDetails: PersonDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            DetailsRepository.setData(context, personDetails = personDetails)
            getPersonList()
        }
    }
}