package com.example.countries.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.countries.R

/*
fun String.myExtension(myParameter:String){
    println(myParameter)
*/

fun ImageView.downloadFromUrl(url:String?,progressDrawable: CircularProgressDrawable){

    val options=RequestOptions
        .placeholderOf(progressDrawable)
        .error(R.mipmap.ic_launcher_round)


    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)


}

fun placeHolderProgressBar(context:Context):CircularProgressDrawable{
        return CircularProgressDrawable(context).apply {

            strokeWidth=8f
            centerRadius=40f
            start()

        }



}
@BindingAdapter("android_downloadUrl")
fun downloadImage(view: ImageView,url: String?){
    view.downloadFromUrl(url, placeHolderProgressBar(view.context))


}