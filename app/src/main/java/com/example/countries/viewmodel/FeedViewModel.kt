package com.example.countries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.model.Country
import com.example.countries.service.CountryAPIService
import com.example.countries.service.CountryDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application){
    private val countryApiService=CountryAPIService()
    private val disposable=CompositeDisposable()
    val countries=MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<Boolean>()
    val countryLoading=MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()


    }

    private fun getDataFromAPI(){
        countryLoading.value=true
        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        storeInSqlLite(t)

                    }

                    override fun onError(e: Throwable) {
                        countryError.value=true
                        countryLoading.value=false
                        e.printStackTrace()

                    }

                })
        )


    }

    private fun showCountries(countryList:List<Country>){
        countries.value=countryList
        countryError.value=false
        countryLoading.value=false


    }
    private fun storeInSqlLite(list: List<Country>){
        launch {

            val dao=CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listlong=dao.insertAll(*list.toTypedArray())
            var i =0
            while (i<list.size){

                list[i].uuid=listlong[i].toInt()
                i=i+1

            }
            showCountries(list)
        }




    }

}