package com.berkesoft.retrofitkotlin.services

import com.berkesoft.retrofitkotlin.model.CyrptoModel
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CyrptoApi {

    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Observable<List<CyrptoModel>>
}