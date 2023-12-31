package com.example.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.countries.R
import com.example.countries.databinding.FragmentCountryBinding
import com.example.countries.util.downloadFromUrl
import com.example.countries.util.placeHolderProgressBar
import com.example.countries.view.CountryFragmentArgs
import com.example.countries.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment() {
    private var countryUuid=0
    private lateinit var viewModel:CountryViewModel
    private lateinit var dataBinding:FragmentCountryBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=FragmentCountryBinding.inflate(inflater)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {

            countryUuid=CountryFragmentArgs.fromBundle(it).CountryUuid

        }

        viewModel=ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)


        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            country->country?.let {
                dataBinding.selectedCountry=it

                /*
                countryName.text=country.countryName
                countryCapital.text=country.countryCapital
                countryCurrency.text=country.countryCurrency
                countryLanguage.text=country.capitalLanguage
                countryRegion.text=country.countryRegion
                context?.let {
                    country.imageUrl?.let { it1 -> countryImage.downloadFromUrl(it1, placeHolderProgressBar(it)) }


                }

                 */


        }
        })


    }


}