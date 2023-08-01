package com.example.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.model.Country

class CountryViewModel: ViewModel() {
    val countryLiveData=MutableLiveData<Country>()

    fun getDataFromRoom(){
        val country=Country("Turkey","Asia","Ankara","Try","Turkce","")
        countryLiveData.value=country

    }
}