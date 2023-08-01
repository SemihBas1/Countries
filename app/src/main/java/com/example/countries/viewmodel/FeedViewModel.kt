package com.example.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.model.Country

class FeedViewModel : ViewModel() {
    val countries=MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<Boolean>()
    val countryLoading=MutableLiveData<Boolean>()

    fun refreshData(){

        val country=Country("Turkey","Asia","Ankara","Try","Turkce","")
        val country2=Country("Fransa","Europe","Paris","Eur","French","")
        val country3=Country("Almanya","Europe","Brrlim","Eur","German","")

        val countryList= arrayListOf<Country>(country,country2,country3)
        countries.value=countryList
        countryError.value=false
        countryLoading.value=false


    }

}