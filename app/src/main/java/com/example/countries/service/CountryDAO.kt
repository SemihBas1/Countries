package com.example.countries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.countries.model.Country

@Dao
interface CountryDAO {

    @Insert
    suspend fun insertAll(vararg countries:Country):List<Long>

    //Insert -> INSERT INTO
    //Suspend -> coroutine,pause &resume
    //vararg -> Multiple country object
    //list<Long> -> primary keys


    @Query("SELECT * FROM country")
    suspend fun getAllCountries():List<Country>

    @Query("SELECT * FROM country WHERE uuid=:countryId")
    suspend fun getCountry(countryId:Int):Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}